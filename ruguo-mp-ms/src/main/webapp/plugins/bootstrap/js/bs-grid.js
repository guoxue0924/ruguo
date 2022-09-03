/*!
 * jQuery Bootgrid v1.3.1
 * update hl - 2017-04-16
 * add column setting (data-visibletitle="true")
 * add showLoading/closeLoading
 * modify checkbox single
 */
(function (root, factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module unless amdModuleId is set
        define(["jquery", 'dialog'], function (a0, a1) {
            return (factory(a0, a1));
        });
    } else if (typeof module === 'object' && module.exports) {
        // Node. Does not work with strict CommonJS, but
        // only CommonJS-like environments that support module.exports,
        // like Node.
        module.exports = factory(require("jquery"));
    } else {
        factory(root["jQuery"]);
    }
}(this, function ($, dialog) {
//    (function ($) {
    	'use strict';
        // GRID INTERNAL FIELDS
        // ====================

        var namespace = ".rs.jquery.bootgrid";

        // GRID INTERNAL FUNCTIONS
        // =====================

        function appendRow(row) {
            var that = this;

            function exists(item) {
                return that.identifier && item[that.identifier] === row[that.identifier];
            }

            if (!this.rows.contains(exists)) {
                this.rows.push(row);
                return true;
            }

            return false;
        }

        function findFooterAndHeaderItems(selector) {
            var footer = (this.footer) ? this.footer.find(selector) : $(),
                header = (this.header) ? this.header.find(selector) : $();
            return $.merge(footer, header);
        }

        function getParams(context) {
            return (context) ? $.extend({}, this.cachedParams, {ctx: context}) :
                this.cachedParams;
        }

        function getRequest() {
            var that = this;

            if (!$.isEmptyObject(that.initParamColsort)) {
                $.each(that.initParamColsort, function (k, v) {
                    $.each(that.columns, function (j, column) {
                        if (column.sortableColumn == k) {
                            that.sortDictionary[column.id] = v;
                        }
                    });
                });
            }

            var sortObj = {};
            if (that.sortDictionary) {
                $.each(that.sortDictionary, function (k, v) {
                    var col = {};
                    $.each(that.columns, function (j, column) {
                        if (column.id == k) {
                            col = column;
                            return false;
                        }
                    });
                    var c = col.sortableColumn;
                    if (c !== '') {
                        sortObj[c] = v;
                    }
                });
            }


            var request = {
                    current: this.current,
                    rowCount: this.rowCount,
//                	sort: this.sortDictionary,
                    colsort: sortObj,
                    limitCount: this.limitCount,
                    searchPhrase: this.searchPhrase
                },
                post = this.options.post;

            post = ($.isFunction(post)) ? post() : post;

            if (!this.options.emptyPost) {
                if ($.isEmptyObject(post)) {
                    return false;
                }
            }

            return this.options.requestHandler($.extend(true, request, post));

        }

        function getCssSelector(css) {
            return "." + $.trim(css).replace(/\s+/gm, ".");
        }

        function getUrl() {
            var url = this.options.url;
            return ($.isFunction(url)) ? url() : url;
        }

        function init() {
            if (this.options.ajax) {
                showLoading.call();
            }

            this.element.trigger("initialize" + namespace);
            renderFixedTable.call(this);
            loadColumns.call(this); // Loads columns from HTML thead tag
            this.selection = this.options.selection && this.identifier != null;
            loadRows.call(this); // Loads rows from HTML tbody tag if ajax is false
            prepareTable.call(this);
            renderTableHeader.call(this);
            renderSearchField.call(this);
            renderActions.call(this);
            loadData.call(this);

            this.element.trigger("initialized" + namespace);
        }

        function highlightAppendedRows(rows) {
            if (this.options.highlightRows) {
                // todo: implement
            }
        }

        function isVisible(column) {
            return column.visible;
        }

        function loadColumns() {
            var that = this,
                firstHeadRow = this.element.find("thead > tr").first(),
                sorted = false;

            /*jshint -W018*/
            firstHeadRow.children().each(function () {
                var $this = $(this),
                    data = $this.data(),
                    column = {
                        id: data.columnId,
                        identifier: that.identifier == null && data.identifier || false,
                        converter: that.options.converters[data.converter || data.type] || that.options.converters["string"],
                        text: $this.text(),
                        align: data.align || "left",
                        headerAlign: data.headeralign || "center",
                        cssClass: data.cssclass || "",
                        headerCssClass: data.headercssclass || "",
                        formatter: that.options.formatters[data.formatter] || null,
                        order: (!sorted && (data.order === "asc" || data.order === "desc")) ? data.order : null,
                        searchable: !(data.searchable === false), // default: true
                        sortable: (data.sortable === true), // default: false
                        sortableColumn: (data.sortable === true && typeof data.sortableColumn !== 'undefined' && data.sortableColumn !== '') ? data.sortableColumn : '',
                        visible: !(data.visible === false), // default: true
                        visibleInSelection: !(data.visibleinselection === false), // default: true
                        width: ($.isNumeric(data.width)) ? data.width + "px" : (typeof(data.width) === "string") ? data.width : null,
                        visibletitle: data.visibletitle || false
                    };
                that.columns.push(column);
                if (column.order != null) {
                    that.sortDictionary[column.id] = column.order;
                }

                // Prevents multiple identifiers
                if (column.identifier) {
                    that.identifier = column.id;
                    that.converter = column.converter;
                }

                // ensures that only the first order will be applied in case of multi sorting is disabled
                if (!that.options.multiSort && column.order != null) {
                    sorted = true;
                }
            });
            /*jshint +W018*/
        }

        /*
         response = {
         current: 1,
         rowCount: 10,
         rows: [{}, {}],
         sort: [{ "columnId": "asc" }],
         total: 101
         }
         */

        function loadData() {
            var that = this;

            this.element._bgBusyAria(true).trigger("load" + namespace);
            if (this.options.ajax) {
                showLoading.call();
            }


            function containsPhrase(row) {
                var column,
                    searchPattern = new RegExp(that.searchPhrase, (that.options.caseSensitive) ? "g" : "gi");

                for (var i = 0; i < that.columns.length; i++) {
                    column = that.columns[i];
                    if (column.searchable && column.visible &&
                        column.converter.to(row[column.id]).search(searchPattern) > -1) {
                        return true;
                    }
                }
                if (this.options.ajax) {
                    closeLoading.call();
                }

                return false;
            }

            function update(rows, total) {
                that.currentRows = rows;
                setTotals.call(that, total);

                if (!that.options.keepSelection) {
                    that.selectedRows = [];
                    that.selectedRowsObj = [];
                }
                that.initParamColsort = {};
                renderRows.call(that, rows);
                renderInfos.call(that);
                renderColResizable.call(that);
                renderPagination.call(that);

                that.element._bgBusyAria(false).trigger("loaded" + namespace);
            }

            if (this.options.ajax) {
                var request = getRequest.call(this),
                    url = getUrl.call(this);

                if (!request) {
                    closeLoading.call();
                    return false;
                }
                if (url == null || typeof url !== "string" || url.length === 0) {
                    throw new Error("Url setting must be a none empty string or a function that returns one.");
                }

                // aborts the previous ajax request if not already finished or failed
                if (this.xqr) {
                    this.xqr.abort();
                }

                var settings = {
                    url: url,
                    data: JSON.stringify(request),//json序列化
                    datatype: "json", //此处不能省略
                    contentType: "application/json; charset=utf-8",//此处不能省略
                    success: function (response, textStatus) {
                        that.xqr = null;
                        if (typeof (response) === "string") {
                            try {
                                response = $.parseJSON(response);
                            } catch (err) {
                                closeLoading.call();
                                dialog.alert("数据处理异常");
                                return;
                            }
                        }

                        response = that.options.responseHandler(response);

                        that.current = response.current;
                        update(response.rows, response.total);
                        closeLoading.call();
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        closeLoading.call();
                        if ("4081" == jqXHR.status) {
                            dialog.alert({
                                message: "登陆超时，请重新登录",
                                callback: function (result) {
                                    window.location.href = webAppPath + "/login";
                                }
                            });
                        }
                        that.xqr = null;

                        if (textStatus !== "abort") {
                            renderNoResultsRow.call(that); // overrides loading mask
                            that.element._bgBusyAria(false).trigger("loaded" + namespace);
                        }
                    }
                };
                settings = $.extend(this.options.ajaxSettings, settings);

                this.xqr = $.ajax(settings);
            }
            else {
                var rows = (this.searchPhrase.length > 0) ? this.rows.where(containsPhrase) : this.rows,
                    total = rows.length;
                if (this.rowCount !== -1) {
                    rows = rows.page(this.current, this.rowCount);
                }

                // todo: improve the following comment
                // setTimeout decouples the initialization so that adding event handlers happens before
                window.setTimeout(function () {
                    update(rows, total);
                }, 10);
            }

        }

        function loadRows() {
            if (!this.options.ajax) {
                var that = this,
                    rows = this.element.find("tbody > tr");

                rows.each(function () {
                    var $this = $(this),
                        cells = $this.children("td"),
                        row = {};

                    $.each(that.columns, function (i, column) {
                        row[column.id] = column.converter.from(cells.eq(i).text());
                    });

                    appendRow.call(that, row);
                });

                setTotals.call(this, this.rows.length);
                sortRows.call(this);
            }
        }

        function setTotals(total) {
            this.total = total;
            this.totalPages = (this.rowCount === -1) ? 1 :
                Math.ceil(this.total / this.rowCount);
        }

        function prepareTable() {
            var tpl = this.options.templates,
                wrapper = (this.element.parent().hasClass(this.options.css.responsiveTable) || this.element.parent().hasClass(this.options.css.fixedTable)) ?
                    this.element.parent() : this.element;

            this.element.addClass(this.options.css.table);

            // checks whether there is an tbody element; otherwise creates one
            if (this.element.children("tbody").length === 0) {
                this.element.append(tpl.body);
            }

            if (this.options.navigation & 1) {
                this.header = $(tpl.header.resolve(getParams.call(this, {id: this.element._bgId() + "-header"})));
                wrapper.before(this.header);
            }

            if (this.options.navigation & 2) {
                this.footer = $(tpl.footer.resolve(getParams.call(this, {id: this.element._bgId() + "-footer"})));
                wrapper.after(this.footer);
            }
        }

        function renderActions() {
            if (this.options.navigation !== 0) {
                var css = this.options.css,
                    selector = getCssSelector(css.actions),
                    actionItems = findFooterAndHeaderItems.call(this, selector);

                if (actionItems.length > 0) {
                    var that = this,
                        tpl = this.options.templates,
                        actions = $(tpl.actions.resolve(getParams.call(this)));

                    // Refresh Button
                    if (this.options.ajax && this.options.refreshButton) {
                        var refreshIcon = tpl.icon.resolve(getParams.call(this, {iconCss: css.iconRefresh})),
                            refresh = $(tpl.actionButton.resolve(getParams.call(this,
                                {content: refreshIcon, text: this.options.labels.refresh})))
                                .on("click" + namespace, function (e) {
                                    // todo: prevent multiple fast clicks (fast click detection)
                                    e.stopPropagation();
                                    that.current = 1;
                                    loadData.call(that);
                                });
                        actions.append(refresh);
                    }

                    // Row count selection
                    if (this.options.rowCountSelection) {
                        renderRowCountSelection.call(this, actions);
                    }

                    // limit row count
                    if (this.options.limitCountSelection) {
                        renderLimitCountSelection.call(this, actions);
                    }
                    // Column selection
                    renderColumnSelection.call(this, actions);

                    replacePlaceHolder.call(this, actionItems, actions);
                }
            }
        }

        function renderColumnSelection(actions) {
            if (this.options.columnSelection && this.columns.length > 1) {
                var that = this,
                    css = this.options.css,
                    tpl = this.options.templates,
                    icon = tpl.icon.resolve(getParams.call(this, {iconCss: css.iconColumns})),
                    dropDown = $(tpl.actionDropDown.resolve(getParams.call(this, {
                        content: icon,
                        title: this.options.labels.columnSelection
                    }))),
                    selector = getCssSelector(css.dropDownItem),
                    checkboxSelector = getCssSelector(css.dropDownItemCheckbox),
                    itemsSelector = getCssSelector(css.dropDownMenuItems);

                $.each(this.columns, function (i, column) {
                    if (column.visibleInSelection) {
                        var item = $(tpl.actionDropDownCheckboxItem.resolve(getParams.call(that,
                            {name: column.id, label: column.text, checked: column.visible})))
                            .on("click" + namespace, selector, function (e) {
                                e.stopPropagation();

                                var $this = $(this),
                                    checkbox = $this.find(checkboxSelector);
                                if (!checkbox.prop("disabled")) {
                                    column.visible = checkbox.prop("checked");
                                    var enable = that.columns.where(isVisible).length > 1;
                                    $this.parents(itemsSelector).find(selector + ":has(" + checkboxSelector + ":checked)")
                                        ._bgEnableAria(enable).find(checkboxSelector)._bgEnableField(enable);

                                    that.element.find("tbody").empty(); // Fixes an column visualization bug
                                    renderTableHeader.call(that);
                                    loadData.call(that);
                                }
                            });
                        dropDown.find(getCssSelector(css.dropDownMenuItems)).append(item);
                    }
                });
                actions.append(dropDown);
            }
        }

        function renderInfos() {
            if (this.options.navigation !== 0) {
                var selector = getCssSelector(this.options.css.infos),
                    infoItems = findFooterAndHeaderItems.call(this, selector);

                if (infoItems.length > 0) {
                    var end = (this.current * this.rowCount),
                        infos = $(this.options.templates.infos.resolve(getParams.call(this, {
                            end: (this.total === 0 || end === -1 || end > this.total) ? this.total : end,
                            start: (this.total === 0) ? 0 : (end - this.rowCount + 1),
                            total: this.total
                        })));

                    replacePlaceHolder.call(this, infoItems, infos);
                }
            }
        }

        function renderNoResultsRow() {
            var tbody = this.element.children("tbody").first(),
                tpl = this.options.templates,
                count = this.columns.where(isVisible).length;

            if (this.selection) {
                count = count + 1;
            }
            var nosultsPadding = this.element.parent('.table-fixed').width() / 2 - 70;
            var noResultsStyle = "padding-left:" + nosultsPadding + "px;text-align:left";
            var nosultslabel = tpl.noResults.resolve(getParams.call(this, {
                columns: count,
                noResultsStyle: noResultsStyle
            }));
            tbody.html(nosultslabel);
        }

        function renderPagination() {
            if (this.options.navigation !== 0) {
                var selector = getCssSelector(this.options.css.pagination),
                    paginationItems = findFooterAndHeaderItems.call(this, selector)._bgShowAria(this.rowCount !== -1),
                    css = this.options.css,
                    labels = this.options.labels;

                if (this.rowCount !== -1 && paginationItems.length > 0) {
                    var tpl = this.options.templates,
                        current = this.current,
                        totalPages = this.totalPages,
                        pagination = $(tpl.pagination.resolve(getParams.call(this))),
                        offsetRight = totalPages - current,
                        offsetLeft = (this.options.padding - current) * -1,
                        startWith = ((offsetRight >= this.options.padding) ?
                            Math.max(offsetLeft, 1) :
                            Math.max((offsetLeft - this.options.padding + offsetRight), 1)),
                        maxCount = this.options.padding * 2 + 1,
                        count = (totalPages >= maxCount) ? maxCount : totalPages;

                    renderPaginationItem.call(this, pagination, "first", css.paginationFirst, "first", labels.paginationFirst)
                        ._bgEnableAria(current > 1);
                    renderPaginationItem.call(this, pagination, "prev", css.paginationPrev, "prev", labels.paginationPrev)
                        ._bgEnableAria(current > 1);

                    for (var i = 0; i < count; i++) {
                        var pos = i + startWith;
                        renderPaginationItem.call(this, pagination, pos, pos, "page-" + pos, "")
                            ._bgEnableAria()._bgSelectAria(pos === current);
                    }

                    if (count === 0) {
                        renderPaginationItem.call(this, pagination, 1, 1, "page-" + 1, "")
                            ._bgEnableAria(false)._bgSelectAria();
                    }

                    renderPaginationItem.call(this, pagination, "next", css.paginationNext, "next", labels.paginationNext)
                        ._bgEnableAria(totalPages > current);
                    renderPaginationItem.call(this, pagination, "last", css.paginationLast, "last", labels.paginationLast)
                        ._bgEnableAria(totalPages > current);

                    replacePlaceHolder.call(this, paginationItems, pagination);
                }
            }
        }

        function renderPaginationItem(list, page, text, markerCss, title) {
            var that = this,
                tpl = this.options.templates,
                css = this.options.css,
                values = getParams.call(this, {css: markerCss, text: text, page: page, title: title}),
                item = $(tpl.paginationItem.resolve(values))
                    .on("click" + namespace, getCssSelector(css.paginationButton), function (e) {
                        e.stopPropagation();
                        e.preventDefault();

                        var $this = $(this),
                            parent = $this.parent();
                        if (!parent.hasClass("active") && !parent.hasClass("disabled")) {
                            var commandList = {
                                first: 1,
                                prev: that.current - 1,
                                next: that.current + 1,
                                last: that.totalPages
                            };
                            var command = $this.data("page");
                            that.current = commandList[command] || command;
                            loadData.call(that);
                        }
                        $this.trigger("blur");
                    });

            list.append(item);
            return item;
        }

        function renderLimitCountSelection(actions) {
            var that = this,
                LimitCountList = this.options.limitCount;

            function getText(value) {
                return (value === -1) ? that.options.labels.all : value;
            }

            if ($.isArray(LimitCountList)) {
                var css = this.options.css,
                    tpl = this.options.templates,
                    dropDown = $(tpl.actionDropDown.resolve(getParams.call(this, {
                        content: getText(this.limitCount),
                        title: this.options.labels.limitCountSelection
                    }))),
                    menuSelector = getCssSelector(css.dropDownMenu),
                    menuTextSelector = getCssSelector(css.dropDownMenuText),
                    menuItemsSelector = getCssSelector(css.dropDownMenuItems),
                    menuItemSelector = getCssSelector(css.dropDownItemButton);

                $.each(LimitCountList, function (index, value) {
                    var item = $(tpl.actionDropDownItem.resolve(getParams.call(that,
                        {text: getText(value), action: value})))
                        ._bgSelectAria(value === that.limitCount)
                        .on("click" + namespace, menuItemSelector, function (e) {
                            e.preventDefault();
                            var $this = $(this),
                                newLimitCount = $this.data("action");
                            if (newLimitCount !== that.limitCount) {
                                // todo: sophisticated solution needed for calculating which page is selected
                                that.current = 1; // that.rowCount === -1 ---> All
//                                that.rowCount = newRowCount;
                                that.limitCount = newLimitCount;
                                $this.parents(menuItemsSelector).children().each(function () {
                                    var $item = $(this),
                                        currentLimitCount = $item.find(menuItemSelector).data("action");
                                    $item._bgSelectAria(currentLimitCount === newLimitCount);
                                });
                                $this.parents(menuSelector).find(menuTextSelector).text(getText(newLimitCount));
                                loadData.call(that);
                            }
                        });
                    dropDown.find(menuItemsSelector).append(item);
                });
                actions.append(dropDown);
            }
        }

        function renderRowCountSelection(actions) {
            var that = this,
                rowCountList = this.options.rowCount;

            function getText(value) {
                return (value === -1) ? that.options.labels.all : value;
            }

            if ($.isArray(rowCountList)) {
                var css = this.options.css,
                    tpl = this.options.templates,
                    dropDown = $(tpl.actionDropDown.resolve(getParams.call(this, {
                        content: getText(this.rowCount),
                        title: this.options.labels.rowCountSelection
                    }))),
                    menuSelector = getCssSelector(css.dropDownMenu),
                    menuTextSelector = getCssSelector(css.dropDownMenuText),
                    menuItemsSelector = getCssSelector(css.dropDownMenuItems),
                    menuItemSelector = getCssSelector(css.dropDownItemButton);

                $.each(rowCountList, function (index, value) {
                    var item = $(tpl.actionDropDownItem.resolve(getParams.call(that,
                        {text: getText(value), action: value})))
                        ._bgSelectAria(value === that.rowCount)
                        .on("click" + namespace, menuItemSelector, function (e) {
                            e.preventDefault();

                            var $this = $(this),
                                newRowCount = $this.data("action");
                            if (newRowCount !== that.rowCount) {
                                // todo: sophisticated solution needed for calculating which page is selected
                                that.current = 1; // that.rowCount === -1 ---> All
                                that.rowCount = newRowCount;
                                $this.parents(menuItemsSelector).children().each(function () {
                                    var $item = $(this),
                                        currentRowCount = $item.find(menuItemSelector).data("action");
                                    $item._bgSelectAria(currentRowCount === newRowCount);
                                });
                                $this.parents(menuSelector).find(menuTextSelector).text(getText(newRowCount));
                                loadData.call(that);
                            }
                        });
                    dropDown.find(menuItemsSelector).append(item);
                });
                actions.append(dropDown);
            }
        }

        function renderFixedTable() {

            // if (!that.options.keepSelection) {

            // }

            var that = this;
            if (!that.element.closest('.table-fixed').length) {
                // $self.addClass('fht-table');
                that.element.wrap('<div class="table-fixed"></div>');
                that.element.css({'margin-bottom': '0'});
            }
        }

        function renderRows(rows) {
            if (typeof(rows) != "undefined" && rows.length > 0) {
                var that = this,
                    css = this.options.css,
                    tpl = this.options.templates,
                    tbody = this.element.children("tbody").first(),
                    allRowsSelected = true,
                    html = "";

                $.each(rows, function (index, row) {
                    var cells = "",
                        rowAttr = " data-row-id=\"" + ((that.identifier == null) ? index : row[that.identifier]) + "\"",
                        rowCss = "";

                    if (that.selection) {
                        var selected = ($.inArray(row[that.identifier], that.selectedRows) !== -1),
                            selectBox = tpl.select.resolve(getParams.call(that,
                                {type: "checkbox", value: row[that.identifier], checked: selected}));
                        cells += tpl.cell.resolve(getParams.call(that, {
                            content: selectBox,
                            css: css.selectCell,
                            title: ''
                        }));
                        allRowsSelected = (allRowsSelected && selected);
                        if (selected) {
                            rowCss += css.selected;
                            rowAttr += " aria-selected=\"true\"";
                        }
                    }

                    var status = row.status != null && that.options.statusMapping[row.status];
                    if (status) {
                        rowCss += status;
                    }

                    $.each(that.columns, function (j, column) {
                        if (column.visible) {
                            var oldvalue = column.converter.to(row[column.id]);
                            var fvalue = ($.isFunction(column.formatter)) ?
                                    column.formatter.call(that, column, row) :
                                    oldvalue,
                                cssClass = (column.cssClass.length > 0) ? " " + column.cssClass : "";
                            var value, title;
                            if (typeof(fvalue) == 'object') {
                                title = fvalue.title;
                                value = fvalue.a;
                            } else {
                                value = fvalue;
                                title = ((typeof(oldvalue) == 'string') ? oldvalue : value);
                            }
                            title = (typeof title !== 'undefined') ? title : '';
                            var overflow = '';
                            try {
                                overflow = ($(value).is('select') || $(value).hasClass('btn-group')) ? 'overflow: visible;' : '';
                            } catch (err) {
                            }
                            cells += tpl.cell.resolve(getParams.call(that, {
                                content: (value == null || value === "") ? "&nbsp;" : value,
                                css: ((column.align === "right") ? css.right : (column.align === "center") ?
                                    css.center : css.left) + cssClass,
                                // title: (column.visibletitle) ? oldvalue : "",
                                title: (column.visibletitle) ? title : "",
                                style: ((column.width == null || column.width === "") ? "" : "width:" + column.width + ";") + overflow
                            }));
                        }
                    });

                    if (rowCss.length > 0) {
                        rowAttr += " class=\"" + rowCss + "\"";
                    }
                    html += tpl.row.resolve(getParams.call(that, {attr: rowAttr, cells: cells}));
                });

                // sets or clears multi selectbox state
                that.element.find("thead " + getCssSelector(that.options.css.selectBox))
                    .prop("checked", allRowsSelected);

                tbody.html(html);

                registerRowEvents.call(this, tbody);
            }
            else {
                renderNoResultsRow.call(this);
            }
        }

        function registerRowEvents(tbody) {
            var that = this,
                selectBoxSelector = getCssSelector(this.options.css.selectBox);

            if (this.selection) {
                tbody.off("click" + namespace, selectBoxSelector)
                    .on("click" + namespace, selectBoxSelector, function (e) {
                        e.stopPropagation();

                        var $this = $(this),
                            id = that.converter.from($this.val());

                        if ($this.prop("checked")) {
                            that.select([id]);
                        }
                        else {
                            that.deselect([id]);
                        }
                    });
            }

            tbody.off("click" + namespace, "> tr")
                .on("click" + namespace, "> tr", function (e) {
                    //e.stopPropagation();

                    var $this = $(this),
                        id = (that.identifier == null) ? $this.data("row-id") :
                            that.converter.from($this.data("row-id") + ""),
                        row = (that.identifier == null) ? that.currentRows[id] :
                            that.currentRows.first(function (item) {
                                return item[that.identifier] === id;
                            });

                    if (that.selection && that.options.rowSelect) {
                        if ($this.hasClass(that.options.css.selected)) {
                            that.deselect([id]);
                        }
                        else {
                            //var a = new Array();
                            //a.push(id);
                            that.select([id]);
                        }
                    }

                    that.element.trigger("click" + namespace, [that.columns, row]);
                });
        }

        function renderSearchField() {
            if (this.options.navigation !== 0) {
                var css = this.options.css,
                    selector = getCssSelector(css.search),
                    searchItems = findFooterAndHeaderItems.call(this, selector);

                if (searchItems.length > 0) {
                    var that = this,
                        tpl = this.options.templates,
                        timer = null, // fast keyup detection
                        currentValue = "",
                        searchFieldSelector = getCssSelector(css.searchField),
                        search = $(tpl.search.resolve(getParams.call(this))),
                        searchField = (search.is(searchFieldSelector)) ? search :
                            search.find(searchFieldSelector);

                    searchField.on("keyup" + namespace, function (e) {
                        e.stopPropagation();
                        var newValue = $(this).val();
                        if (currentValue !== newValue || (e.which === 13 && newValue !== "")) {
                            currentValue = newValue;
                            if (e.which === 13 || newValue.length === 0 || newValue.length >= that.options.searchSettings.characters) {
                                window.clearTimeout(timer);
                                timer = window.setTimeout(function () {
                                    executeSearch.call(that, newValue);
                                }, that.options.searchSettings.delay);
                            }
                        }
                    });

                    replacePlaceHolder.call(this, searchItems, search);
                }
            }
        }

        function executeSearch(phrase) {
            if (this.searchPhrase !== phrase) {
                this.current = 1;
                this.searchPhrase = phrase;
                loadData.call(this);
            }
        }

        function renderTableHeader() {
            var that = this,
                headerRow = this.element.find("thead > tr"),
                css = this.options.css,
                tpl = this.options.templates,
                html = "",
                sorting = this.options.sorting;

            if (this.selection) {
                var selectBox = (this.options.multiSelect) ?
                    tpl.select.resolve(getParams.call(that, {type: "checkbox", value: "all"})) : "";
                html += tpl.rawHeaderCell.resolve(getParams.call(that, {
                    content: selectBox,
                    css: css.selectCell
                }));
            }

            $.each(this.columns, function (index, column) {
                if (column.visible) {
                    var sortOrder = that.sortDictionary[column.id],
                        iconCss = ((sorting && sortOrder && sortOrder === "asc") ? css.iconUp :
                            (sorting && sortOrder && sortOrder === "desc") ? css.iconDown : ""),
                        icon = tpl.icon.resolve(getParams.call(that, {iconCss: iconCss})),
                        align = column.headerAlign,
                        cssClass = (column.headerCssClass.length > 0) ? " " + column.headerCssClass : "";
                    html += tpl.headerCell.resolve(getParams.call(that, {
                        column: column, icon: icon, sortable: sorting && column.sortable && css.sortable || "",
                        css: ((align === "right") ? css.right : (align === "center") ?
                            css.center : css.left) + cssClass,
                        style: (column.width == null || column.width === "") ? "" : "width:" + column.width + ";"
                    }));
                }
            });

            headerRow.html(html);

            if (sorting) {
                var sortingSelector = getCssSelector(css.sortable);
                headerRow.off("click" + namespace, sortingSelector)
                    .on("click" + namespace, sortingSelector, function (e) {
                        e.preventDefault();

                        setTableHeaderSortDirection.call(that, $(this));
                        sortRows.call(that);
                        loadData.call(that);
                    });
            }

            // todo: create a own function for that piece of code
            if (this.selection && this.options.multiSelect) {
                var selectBoxSelector = getCssSelector(css.selectBox);
                headerRow.off("click" + namespace, selectBoxSelector)
                    .on("click" + namespace, selectBoxSelector, function (e) {
                        e.stopPropagation();

                        if ($(this).prop("checked")) {
                            that.select();
                        }
                        else {
                            that.deselect();
                        }
                    });
            }
        }

        function setTableHeaderSortDirection(element) {
            var css = this.options.css,
                iconSelector = getCssSelector(css.icon),
                columnId = element.data("column-id") || element.parents("th").first().data("column-id"),
                sortOrder = this.sortDictionary[columnId],
                icon = element.find(iconSelector);

            if (!this.options.multiSort) {
                element.parents("tr").first().find(iconSelector).removeClass(css.iconDown + " " + css.iconUp);
                this.sortDictionary = {};
            }

            if (sortOrder && sortOrder === "asc") {
                this.sortDictionary[columnId] = "desc";
                icon.removeClass(css.iconUp).addClass(css.iconDown);
            }
            else if (sortOrder && sortOrder === "desc") {
                if (this.options.multiSort) {
                    var newSort = {};
                    for (var key in this.sortDictionary) {
                        if (key !== columnId) {
                            newSort[key] = this.sortDictionary[key];
                        }
                    }
                    this.sortDictionary = newSort;
                    icon.removeClass(css.iconDown);
                }
                else {
                    this.sortDictionary[columnId] = "asc";
                    icon.removeClass(css.iconDown).addClass(css.iconUp);
                }
            }
            else {
                this.sortDictionary[columnId] = "asc";
                icon.addClass(css.iconUp);
            }
        }

        function replacePlaceHolder(placeholder, element) {
            placeholder.each(function (index, item) {
                // todo: check how append is implemented. Perhaps cloning here is superfluous.
                $(item).before(element.clone(true)).remove();
            });
        }

        function showLoading() {
            dialog.showLoading('正在查询...');
        }

        function closeLoading() {
            dialog.closeLoading();
        }

        function sortRows() {
            var sortArray = [];

            function sort(x, y, current) {
                current = current || 0;
                var next = current + 1,
                    item = sortArray[current];

                function sortOrder(value) {
                    return (item.order === "asc") ? value : value * -1;
                }

                return (x[item.id] > y[item.id]) ? sortOrder(1) :
                    (x[item.id] < y[item.id]) ? sortOrder(-1) :
                        (sortArray.length > next) ? sort(x, y, next) : 0;
            }

            if (!this.options.ajax) {
                var that = this;

                for (var key in this.sortDictionary) {
                    if (this.options.multiSort || sortArray.length === 0) {
                        sortArray.push({
                            id: key,
                            order: this.sortDictionary[key]
                        });
                    }
                }

                if (sortArray.length > 0) {
                    this.rows.sort(sort);
                }
            }
        }

        /**
         * 计算最佳列宽
         */
        function renderColResizable() {
            var that = this;
            if (that.options.colResizable) {
                $(that.element).css({'table-layout': 'auto'});
                var countCol = function () {
                    var t = that.element;
                    var tf = $('.table-fixed').width();
                    var ths = t.find(">thead>tr:first>th,>thead>tr:first>td"); //table headers are obtained
                    var trs = t.find("tbody>tr");	 //but headers can also be included in different ways
                    ths = ths.filter(":visible");
                    var twidth = 0;

                    $.each(ths, function (i, tha) {
                        var th = $(this);
                        var thow = th.outerWidth(true);

                        var str = $.trim(th.text());
                        var bytesCount = 0;
                        for (var k = 0; k < str.length; k++) {
                            var c = str.charAt(k);
                            if (/^[\u0000-\u00ff]$/.test(c)) //匹配双字节
                            {
                                bytesCount += 1;
                            }
                            else {
                                bytesCount += 2;
                            }
                        }
                        var minwidth = bytesCount * 6 + 16 + 2 + 30; //字节长度+16padding+2border+30图标占位
                        if (minwidth > thow) {
                            th.css({'min-width': minwidth + 'px'});
                        } else if (thow > 200) {
                            //TODO
                        }

                    });
                };
                countCol();
                return;
            }
        }

        // GRID PUBLIC CLASS DEFINITION
        // ====================

        /**
         * Represents the jQuery Bootgrid plugin.
         *
         * @class Grid
         * @constructor
         * @param element {Object} The corresponding DOM element.
         * @param options {Object} The options to override default settings.
         * @chainable
         **/
        var Grid = function (element, options) {
            this.element = $(element);
            this.origin = this.element.clone();
            this.options = $.extend(true, {}, Grid.defaults, this.element.data(), options);
            // overrides rowCount explicitly because deep copy ($.extend) leads to strange behaviour
            var rowCount = this.options.rowCount = this.element.data().rowCount || options.rowCount || this.options.rowCount;
            this.columns = [];
            this.current = 1;
            this.currentRows = [];
            this.identifier = null; // The first column ID that is marked as identifier
            this.selection = false;
            this.converter = null; // The converter for the column that is marked as identifier
            this.rowCount = ($.isArray(rowCount)) ? rowCount[0] : rowCount;
            this.limitCount = this.options.limitCountSelection ? this.options.limitCount[0] : -1;
            this.initParamColsort = {};
            this.rows = [];
            this.searchPhrase = "";
            this.selectedRows = [];
            this.selectedRowsObj = [];
            this.sortDictionary = {};
            this.total = 0;
            this.totalPages = 0;
            this.cachedParams = {
                lbl: this.options.labels,
                css: this.options.css,
                ctx: {}
            };
            this.header = null;
            this.footer = null;
            this.xqr = null;
            // todo: implement cache
            if (!$.isEmptyObject(options.initParam)) {
                this.current = typeof options.initParam.current !== 'undefined' ? options.initParam.current : 1;
                this.rowCount = typeof options.initParam.rowCount !== 'undefined' ? options.initParam.rowCount : (($.isArray(rowCount)) ? rowCount[0] : rowCount);
                this.initParamColsort = typeof options.initParam.colsort !== 'undefined' ? options.initParam.colsort : {};
            }
        };

        /**
         * An object that represents the default settings.
         *
         * @static
         * @class defaults
         * @for Grid
         * @example
         *   // Global approach
         *   $.bootgrid.defaults.selection = true;
         * @example
         *   // Initialization approach
         *   $("#bootgrid").bootgrid({ selection = true });
         **/
        Grid.defaults = {
            navigation: 2, // it's a flag: 0 = none, 1 = top, 2 = bottom, 3 = both (top and bottom)
            padding: 2, // page padding (pagination)
            columnSelection: true,
            refreshButton: true,
            rowCountSelection: true,
            limitCountSelection: true,
            rowCount: [10, 25, 50, -1], // rows per page int or array of int (-1 represents "All")
            limitCount: [500, 5000, 50000, -1], // (-1 represents "All")

            /**
             * Enables row selection (to enable multi selection see also `multiSelect`). Default value is `false`.
             *
             * @property selection
             * @type Boolean
             * @default false
             * @for defaults
             * @since 1.0.0
             **/
            selection: false,

            /**
             * Enables multi selection (`selection` must be set to `true` as well). Default value is `false`.
             *
             * @property multiSelect
             * @type Boolean
             * @default false
             * @for defaults
             * @since 1.0.0
             **/
            multiSelect: false,

            /**
             * Enables entire row click selection (`selection` must be set to `true` as well). Default value is `false`.
             *
             * @property rowSelect
             * @type Boolean
             * @default false
             * @for defaults
             * @since 1.1.0
             **/
            rowSelect: false,

            /**
             * Defines whether the row selection is saved internally on filtering, paging and sorting
             * (even if the selected rows are not visible).
             *
             * @property keepSelection
             * @type Boolean
             * @default false
             * @for defaults
             * @since 1.1.0
             **/
            keepSelection: false,

            highlightRows: false, // highlights new rows (find the page of the first new row)
            sorting: true,
            multiSort: false,

            /**
             * General search settings to configure the search field behaviour.
             *
             * @property searchSettings
             * @type Object
             * @for defaults
             * @since 1.2.0
             **/
            searchSettings: {
                /**
                 * The time in milliseconds to wait before search gets executed.
                 *
                 * @property delay
                 * @type Number
                 * @default 250
                 * @for searchSettings
                 **/
                delay: 250,

                /**
                 * The characters to type before the search gets executed.
                 *
                 * @property characters
                 * @type Number
                 * @default 1
                 * @for searchSettings
                 **/
                characters: 1
            },

            /**
             * Defines whether the data shall be loaded via an asynchronous HTTP (Ajax) request.
             *
             * @property ajax
             * @type Boolean
             * @default false
             * @for defaults
             **/
            ajax: false,

            /**
             * Ajax request settings that shall be used for server-side communication.
             * All setting except data, error, success and url can be overridden.
             * For the full list of settings go to http://api.jquery.com/jQuery.ajax/.
             *
             * @property ajaxSettings
             * @type Object
             * @for defaults
             * @since 1.2.0
             **/
            ajaxSettings: {
                /**
                 * Specifies the HTTP method which shall be used when sending data to the server.
                 * Go to http://api.jquery.com/jQuery.ajax/ for more details.
                 * This setting is overriden for backward compatibility.
                 *
                 * @property method
                 * @type String
                 * @default "POST"
                 * @for ajaxSettings
                 **/
                method: "POST"
            },

            /**
             * Enriches the request object with additional properties. Either a `PlainObject` or a `Function`
             * that returns a `PlainObject` can be passed. Default value is `{}`.
             *
             * @property post
             * @type Object|Function
             * @default function (request) { return request; }
             * @for defaults
             * @deprecated Use instead `requestHandler`
             **/
            post: {}, // or use function () { return {}; } (reserved properties are "current", "rowCount", "sort" and "searchPhrase")

            /**
             * Sets the data URL to a data service (e.g. a REST service). Either a `String` or a `Function`
             * that returns a `String` can be passed. Default value is `""`.
             *
             * @property url
             * @type String|Function
             * @default ""
             * @for defaults
             **/
            url: "", // or use function () { return ""; }

            /**
             * Defines whether the search is case sensitive or insensitive.
             *
             * @property caseSensitive
             * @type Boolean
             * @default true
             * @for defaults
             * @since 1.1.0
             **/
            caseSensitive: true,

            // note: The following properties should not be used via data-api attributes

            /**
             * Transforms the JSON request object in what ever is needed on the server-side implementation.
             *
             * @property requestHandler
             * @type Function
             * @default function (request) { return request; }
             * @for defaults
             * @since 1.1.0
             **/
            requestHandler: function (request) {
                return request;
            },

            /**
             * Transforms the response object into the expected JSON response object.
             *
             * @property responseHandler
             * @type Function
             * @default function (response) { return response; }
             * @for defaults
             * @since 1.1.0
             **/
            responseHandler: function (response) {
                return response;
            },

            /**
             * A list of converters.
             *
             * @property converters
             * @type Object
             * @for defaults
             * @since 1.0.0
             **/
            converters: {
                numeric: {
                    from: function (value) {
                        return +value;
                    }, // converts from string to numeric
                    to: function (value) {
                        return value + "";
                    } // converts from numeric to string
                },
                string: {
                    // default converter
                    from: function (value) {
                        return value;
                    },
                    to: function (value) {
                        return $('<span>').text(value).html();
                    }
                }
            },

            /**
             * Contains all css classes.
             *
             * @property css
             * @type Object
             * @for defaults
             **/
            css: {
                actions: "actions btn-group", // must be a unique class name or constellation of class names within the header and footer
                center: "text-center",
                columnHeaderAnchor: "column-header-anchor", // must be a unique class name or constellation of class names within the column header cell
                columnHeaderText: "text",
                dropDownItem: "dropdown-item", // must be a unique class name or constellation of class names within the actionDropDown,
                dropDownItemButton: "dropdown-item-button", // must be a unique class name or constellation of class names within the actionDropDown
                dropDownItemCheckbox: "dropdown-item-checkbox", // must be a unique class name or constellation of class names within the actionDropDown
                dropDownMenu: "dropdown btn-group", // must be a unique class name or constellation of class names within the actionDropDown
                dropDownMenuItems: "dropdown-menu pull-right", // must be a unique class name or constellation of class names within the actionDropDown
                dropDownMenuText: "dropdown-text", // must be a unique class name or constellation of class names within the actionDropDown
                //footer: "bootgrid-footer container-fluid",
                footer: "bootgrid-footer",
                //header: "bootgrid-header container-fluid",
                header: "bootgrid-header",
                icon: "icon glyphicon",
                iconColumns: "glyphicon-th-list",
                iconDown: "glyphicon-chevron-down",
                iconRefresh: "glyphicon-refresh",
                iconSearch: "glyphicon-search",
                iconUp: "glyphicon-chevron-up",
                infos: "text-default", // must be a unique class name or constellation of class names within the header and footer,
                left: "text-left",
                pagination: "pagination", // must be a unique class name or constellation of class names within the header and footer
                paginationButton: "button", // must be a unique class name or constellation of class names within the pagination
                paginationFirst: "<i class='fa fa-angle-double-left' aria-hidden='true'></i>",
                paginationPrev: "<i class='fa fa-angle-left' aria-hidden='true'></i>",
                paginationNext: "<i class='fa fa-angle-right' aria-hidden='true'></i>",
                paginationLast: "<i class='fa fa-angle-double-right' aria-hidden='true'></i>",

                /**
                 * CSS class to select the parent div which activates responsive mode.
                 *
                 * @property responsiveTable
                 * @type String
                 * @default "table-responsive"
                 * @for css
                 * @since 1.1.0
                 **/
                responsiveTable: "table-responsive",

                /**
                 * 表格固定宽度
                 * hl 2017-04-15
                 */
                fixedTable: "table-fixed",
                right: "text-right",
                search: "search form-group", // must be a unique class name or constellation of class names within the header and footer
                searchField: "search-field form-control",
                selectBox: "select-box", // must be a unique class name or constellation of class names within the entire table
                selectCell: "select-cell", // must be a unique class name or constellation of class names within the entire table

                /**
                 * CSS class to highlight selected rows.
                 *
                 * @property selected
                 * @type String
                 * @default "active"
                 * @for css
                 * @since 1.1.0
                 **/
                selected: "active",

                sortable: "sortable",
                table: "bootgrid-table table"
            },

            /**
             * A dictionary of formatters.
             *
             * @property formatters
             * @type Object
             * @for defaults
             * @since 1.0.0
             **/
            formatters: {},

            /**
             * Contains all labels.
             *
             * @property labels
             * @type Object
             * @for defaults
             **/
            labels: {
                all: "全部",
                infos: "显示 {{ctx.start}} 到 {{ctx.end}} 条， 共 {{ctx.total}} 条记录",
                loading: "正在查询中...",
                noResults: "没有找到符合条件的记录！",
                refresh: "刷新数据",
                limitCountSelection: "查询记录最大数",
                rowCountSelection: "每页显示记录数",
                columnSelection: "显示或隐藏列",
                paginationFirst: "首页",
                paginationPrev: "上一页",
                paginationNext: "下一页",
                paginationLast: "末页",
                search: "Search"
            },

            /**
             * Specifies the mapping between status and contextual classes to color rows.
             *
             * @property statusMapping
             * @type Object
             * @for defaults
             * @since 1.2.0
             **/
            statusMapping: {
                /**
                 * Specifies a successful or positive action.
                 *
                 * @property 0
                 * @type String
                 * @for statusMapping
                 **/
                0: "success",

                /**
                 * Specifies a neutral informative change or action.
                 *
                 * @property 1
                 * @type String
                 * @for statusMapping
                 **/
                1: "info",

                /**
                 * Specifies a warning that might need attention.
                 *
                 * @property 2
                 * @type String
                 * @for statusMapping
                 **/
                2: "warning",

                /**
                 * Specifies a dangerous or potentially negative action.
                 *
                 * @property 3
                 * @type String
                 * @for statusMapping
                 **/
                3: "danger"
            },

            /**
             * Contains all templates.
             *
             * @property templates
             * @type Object
             * @for defaults
             **/
            templates: {
                actionButton: "<button class=\"btn btn-default\" type=\"button\" title=\"{{ctx.text}}\">{{ctx.content}}</button>",
                actionDropDown: "<div class=\"{{css.dropDownMenu}}\"><button class=\"btn btn-default dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\" title=\"{{ctx.title}}\"><span class=\"{{css.dropDownMenuText}}\">{{ctx.content}}</span> <span class=\"caret\"></span></button><ul class=\"{{css.dropDownMenuItems}}\" role=\"menu\"></ul></div>",
                actionDropDownItem: "<li><a data-action=\"{{ctx.action}}\" class=\"{{css.dropDownItem}} {{css.dropDownItemButton}}\">{{ctx.text}}</a></li>",
                actionDropDownCheckboxItem: "<li><label class=\"{{css.dropDownItem}}\"><input name=\"{{ctx.name}}\" type=\"checkbox\" value=\"1\" class=\"{{css.dropDownItemCheckbox}}\" {{ctx.checked}} /> {{ctx.label}}</label></li>",
                actions: "<div class=\"{{css.actions}}\"></div>",
                body: "<tbody></tbody>",
                cell: "<td class=\"{{ctx.css}}\" style=\"{{ctx.style}}\" title=\"{{ctx.title}}\">{{ctx.content}}</td>",
                //cell: "<td class=\"{{ctx.css}}\" style=\"{{ctx.style}}\"  data-toggle=\"popover\"  data-content=\"{{ctx.title}}\">{{ctx.content}}</td>",
                //footer: "<div id=\"{{ctx.id}}\" class=\"{{css.footer}}\"><div class=\"row\"><div class=\"col-xs-6\"><p class=\"{{css.pagination}}\"></p></div><div class=\"col-sm-6 infoBar\"><p class=\"{{css.infos}}\"></p></div></div></div>",
                footer: "<div id=\"{{ctx.id}}\" class=\"{{css.footer}}\"><div class=\"row\"><div class=\"col-xs-6\"><p class=\"{{css.pagination}}\"></p></div><div class=\"col-xs-6 infoBar\"><p class=\"{{css.infos}}\"></p><p class=\"{{css.actions}}\"></p></div></div></div>",
                header: "<div id=\"{{ctx.id}}\" class=\"{{css.header}}\"><div class=\"row\"><div class=\"col-xs-12 actionBar\"><p class=\"{{css.search}}\"></p><p class=\"{{css.actions}}\"></p></div></div></div>",
                headerCell: "<th data-column-id=\"{{ctx.column.id}}\" class=\"{{ctx.css}}\" style=\"{{ctx.style}}\"><a href=\"javascript:void(0);\" class=\"{{css.columnHeaderAnchor}} {{ctx.sortable}}\"><span class=\"{{css.columnHeaderText}}\">{{ctx.column.text}}</span>{{ctx.icon}}</a></th>",
                icon: "<span class=\"{{css.icon}} {{ctx.iconCss}}\"></span>",
                infos: "<span class=\"{{css.infos}}\">{{lbl.infos}} </span>",
                loading: "<tr><td colspan=\"{{ctx.columns}}\" class=\"loading\">{{lbl.loading}}</td></tr>",
                noResults: "<tr><td colspan=\"{{ctx.columns}}\" class=\"no-results\" style=\"{{ctx.noResultsStyle}}\">{{lbl.noResults}}</td></tr>",
                pagination: "<ul class=\"{{css.pagination}}\"></ul>",
                paginationItem: "<li class=\"{{ctx.css}}\" title=\"{{ctx.title}}\"><a data-page=\"{{ctx.page}}\" class=\"{{css.paginationButton}}\">{{ctx.text}}</a></li>",
                rawHeaderCell: "<th class=\"{{ctx.css}}\">{{ctx.content}}</th>", // Used for the multi select box
                row: "<tr{{ctx.attr}}>{{ctx.cells}}</tr>",
                search: "<div class=\"{{css.search}}\"><div class=\"input-group\"><span class=\"{{css.icon}} input-group-addon {{css.iconSearch}}\"></span> <input type=\"text\" class=\"{{css.searchField}}\" placeholder=\"{{lbl.search}}\" /></div></div>",
                select: "<input name=\"select\" type=\"{{ctx.type}}\" class=\"{{css.selectBox}}\" value=\"{{ctx.value}}\" {{ctx.checked}} />"
            },
            /**
             * 列拖拽功能
             ***/
            colResizable: true
        };

        /**
         * Appends rows.
         *
         * @method append
         * @param rows {Array} An array of rows to append
         * @chainable
         **/
        Grid.prototype.append = function (rows) {
            if (this.options.ajax) {
                // todo: implement ajax PUT
            }
            else {
                var appendedRows = [];
                for (var i = 0; i < rows.length; i++) {
                    if (appendRow.call(this, rows[i])) {
                        appendedRows.push(rows[i]);
                    }
                }
                sortRows.call(this);
                highlightAppendedRows.call(this, appendedRows);
                loadData.call(this);
                this.element.trigger("appended" + namespace, [appendedRows]);
            }

            return this;
        };

        /**
         * Removes all rows.
         *
         * @method clear
         * @chainable
         **/
        Grid.prototype.clear = function () {
            if (this.options.ajax) {
                // todo: implement ajax POST
            }
            else {
                var removedRows = $.extend([], this.rows);
                this.rows = [];
                this.current = 1;
                this.total = 0;
                loadData.call(this);
                this.element.trigger("cleared" + namespace, [removedRows]);
            }

            return this;
        };

        /**
         * Removes the control functionality completely and transforms the current state to the initial HTML structure.
         *
         * @method destroy
         * @chainable
         **/
        Grid.prototype.destroy = function () {
            // todo: this method has to be optimized (the complete initial state must be restored)
            $(window).off(namespace);
            if (this.options.navigation & 1) {
                this.header.remove();
            }
            if (this.options.navigation & 2) {
                this.footer.remove();
            }
            this.element.before(this.origin).remove();

            return this;
        };

        /**
         * Resets the state and reloads rows.
         *
         * @method reload
         * @chainable
         **/
        Grid.prototype.reload = function () {
            this.current = 1; // reset
            loadData.call(this);

            return this;
        };

        /**
         * Removes rows by ids. Removes selected rows if no ids are provided.
         *
         * @method remove
         * @param [rowsIds] {Array} An array of rows ids to remove
         * @chainable
         **/
        Grid.prototype.remove = function (rowIds) {
            if (this.identifier != null) {
                var that = this;

                if (this.options.ajax) {
                    // todo: implement ajax DELETE
                }
                else {
                    rowIds = rowIds || this.selectedRows;
                    var id,
                        removedRows = [];

                    for (var i = 0; i < rowIds.length; i++) {
                        id = rowIds[i];

                        for (var j = 0; j < this.rows.length; j++) {
                            if (this.rows[j][this.identifier] === id) {
                                removedRows.push(this.rows[j]);
                                this.rows.splice(j, 1);
                                break;
                            }
                        }
                    }

                    this.current = 1; // reset
                    loadData.call(this);
                    this.element.trigger("removed" + namespace, [removedRows]);
                }
            }

            return this;
        };

        /**
         * Searches in all rows for a specific phrase (but only in visible cells).
         * The search filter will be reseted, if no argument is provided.
         *
         * @method search
         * @param [phrase] {String} The phrase to search for
         * @chainable
         **/
        Grid.prototype.search = function (phrase) {
            phrase = phrase || "";

            if (this.searchPhrase !== phrase) {
                var selector = getCssSelector(this.options.css.searchField),
                    searchFields = findFooterAndHeaderItems.call(this, selector);
                searchFields.val(phrase);
            }

            executeSearch.call(this, phrase);


            return this;
        };

        /**
         * Selects rows by ids. Selects all visible rows if no ids are provided.
         * In server-side scenarios only visible rows are selectable.
         *
         * @method select
         * @param [rowsIds] {Array} An array of rows ids to select
         * @chainable
         **/
        Grid.prototype.select = function (rowIds) {
            if (this.selection) {
                rowIds = rowIds || this.currentRows.propValues(this.identifier);

                var id, i,
                    selectedRows = [];

                while (rowIds.length > 0 && !(!this.options.multiSelect && selectedRows.length === 1)) {
                    id = rowIds.pop();
                    if ($.inArray(id, this.selectedRows) === -1) {
                        for (i = 0; i < this.currentRows.length; i++) {
                            if (this.currentRows[i][this.identifier] === id) {
                                selectedRows.push(this.currentRows[i]);
                                this.selectedRows.push(id);
                                this.selectedRowsObj.push(this.currentRows[i]);
                                break;
                            }
                        }
                    }
                }

                if (selectedRows.length > 0) {
                    var selectBoxSelector = getCssSelector(this.options.css.selectBox),
                        selectMultiSelectBox = this.selectedRows.length >= this.currentRows.length;

                    i = 0;
                    while (!this.options.keepSelection && selectMultiSelectBox && i < this.currentRows.length) {
                        selectMultiSelectBox = ($.inArray(this.currentRows[i++][this.identifier], this.selectedRows) !== -1);
                    }
                    this.element.find("thead " + selectBoxSelector).prop("checked", selectMultiSelectBox);

                    if (!this.options.multiSelect) {
                        this.element.find("tbody > tr " + selectBoxSelector + ":checked").not("input[value='" + id + "']")
                            .trigger("click" + namespace);
                    }

                    for (i = 0; i < this.selectedRows.length; i++) {
                        this.element.find("tbody > tr[data-row-id=\"" + this.selectedRows[i] + "\"]")
                            .addClass(this.options.css.selected)._bgAria("selected", "true")
                            .find(selectBoxSelector).prop("checked", true);
                    }

                    this.element.trigger("selected" + namespace, [selectedRows]);
                }
            }

            return this;
        };

        /**
         * Deselects rows by ids. Deselects all visible rows if no ids are provided.
         * In server-side scenarios only visible rows are deselectable.
         *
         * @method deselect
         * @param [rowsIds] {Array} An array of rows ids to deselect
         * @chainable
         **/
        Grid.prototype.deselect = function (rowIds) {
            if (this.selection) {
                rowIds = rowIds || this.currentRows.propValues(this.identifier);

                var id, i, pos,
                    deselectedRows = [];

                while (rowIds.length > 0) {
                    id = rowIds.pop();
                    pos = $.inArray(id, this.selectedRows);
                    if (pos !== -1) {
                        for (i = 0; i < this.currentRows.length; i++) {
                            if (this.currentRows[i][this.identifier] === id) {
                                var pos2 = $.inArray(this.currentRows[i], this.selectedRowsObj);
                                deselectedRows.push(this.currentRows[i]);
                                this.selectedRows.splice(pos, 1);
                                this.selectedRowsObj.splice(pos, 1);
                                break;
                            }
                        }
                    }
                }

                if (deselectedRows.length > 0) {
                    var selectBoxSelector = getCssSelector(this.options.css.selectBox);

                    this.element.find("thead " + selectBoxSelector).prop("checked", false);
                    for (i = 0; i < deselectedRows.length; i++) {
                        this.element.find("tbody > tr[data-row-id=\"" + deselectedRows[i][this.identifier] + "\"]")
                            .removeClass(this.options.css.selected)._bgAria("selected", "false")
                            .find(selectBoxSelector).prop("checked", false);
                    }

                    this.element.trigger("deselected" + namespace, [deselectedRows]);
                }
            }

            return this;
        };

        /**
         * Sorts the rows by a given sort descriptor dictionary.
         * The sort filter will be reseted, if no argument is provided.
         *
         * @method sort
         * @param [dictionary] {Object} A sort descriptor dictionary that contains the sort information
         * @chainable
         **/
        Grid.prototype.sort = function (dictionary) {
            var values = (dictionary) ? $.extend({}, dictionary) : {};

            if (values === this.sortDictionary) {
                return this;
            }

            this.sortDictionary = values;
            renderTableHeader.call(this);
            sortRows.call(this);
            loadData.call(this);

            return this;
        };

        /**
         * Gets a list of the column settings.
         * This method returns only for the first grid instance a value.
         * Therefore be sure that only one grid instance is catched by your selector.
         *
         * @method getColumnSettings
         * @return {Array} Returns a list of the column settings.
         * @since 1.2.0
         **/
        Grid.prototype.getColumnSettings = function () {
            return $.merge([], this.columns);
        };

        /**
         * Gets the current page index.
         * This method returns only for the first grid instance a value.
         * Therefore be sure that only one grid instance is catched by your selector.
         *
         * @method getCurrentPage
         * @return {Number} Returns the current page index.
         * @since 1.2.0
         **/
        Grid.prototype.getCurrentPage = function () {
            return this.current;
        };

        /**
         * Gets the current rows.
         * This method returns only for the first grid instance a value.
         * Therefore be sure that only one grid instance is catched by your selector.
         *
         * @method getCurrentPage
         * @return {Array} Returns the current rows.
         * @since 1.2.0
         **/
        Grid.prototype.getCurrentRows = function () {
            return $.merge([], this.currentRows || []);
        };

        /**
         * Gets a number represents the row count per page.
         * This method returns only for the first grid instance a value.
         * Therefore be sure that only one grid instance is catched by your selector.
         *
         * @method getRowCount
         * @return {Number} Returns the row count per page.
         * @since 1.2.0
         **/
        Grid.prototype.getRowCount = function () {
            return this.rowCount;
        };

        /**
         * Gets the actual search phrase.
         * This method returns only for the first grid instance a value.
         * Therefore be sure that only one grid instance is catched by your selector.
         *
         * @method getSearchPhrase
         * @return {String} Returns the actual search phrase.
         * @since 1.2.0
         **/
        Grid.prototype.getSearchPhrase = function () {
            return this.searchPhrase;
        };

        /**
         * Gets the complete list of currently selected rows.
         * This method returns only for the first grid instance a value.
         * Therefore be sure that only one grid instance is catched by your selector.
         *
         * @method getSelectedRows
         * @return {Array} Returns all selected rows.
         * @since 1.2.0
         **/
        Grid.prototype.getSelectedRows = function () {
            return $.merge([], this.selectedRows);
        };

        Grid.prototype.getSelectedRowsObj = function () {
            return $.merge([], this.selectedRowsObj);
        };
        /**
         * Gets the sort dictionary which represents the state of column sorting.
         * This method returns only for the first grid instance a value.
         * Therefore be sure that only one grid instance is catched by your selector.
         *
         * @method getSortDictionary
         * @return {Object} Returns the sort dictionary.
         * @since 1.2.0
         **/
        Grid.prototype.getSortDictionary = function () {
            return $.extend({}, this.sortDictionary);
        };

        /**
         * Gets a number represents the total page count.
         * This method returns only for the first grid instance a value.
         * Therefore be sure that only one grid instance is catched by your selector.
         *
         * @method getTotalPageCount
         * @return {Number} Returns the total page count.
         * @since 1.2.0
         **/
        Grid.prototype.getTotalPageCount = function () {
            return this.totalPages;
        };

        /**
         * Gets a number represents the total row count.
         * This method returns only for the first grid instance a value.
         * Therefore be sure that only one grid instance is catched by your selector.
         *
         * @method getTotalRowCount
         * @return {Number} Returns the total row count.
         * @since 1.2.0
         **/
        Grid.prototype.getTotalRowCount = function () {
            return this.total;
        };

        /**
         * 动态设置列显示/隐藏
         * 参数：列ID:column[],显示:true/隐藏false
         * hl 2017-05-17 16:20:58
         */
        Grid.prototype.visibleColumns = function (coloumIds, visibled, coloumIds2, visibled2) {
            var that = this;
            var cid;
            if (coloumIds.length > 0) {
                var eachColoumn = function (cid, visibled) {
                    $.each(that.columns, function (j, col) {
                        if (col.id === cid) {
                            col.visible = visibled;
                            col.visibleInSelection = visibled;
                            return false;
                        }
                    });
                };
                while (coloumIds.length > 0) {
                    cid = coloumIds.pop();
                    eachColoumn(cid, visibled);
                }
                if (typeof coloumIds2 !== 'undefined' && coloumIds2.length > 0) {
                    while (coloumIds2.length > 0) {
                        cid = coloumIds2.pop();
                        eachColoumn(cid, visibled2);
                    }
                }
                renderTableHeader.call(this);
                renderActions.call(this);
                loadData.call(this);
            }
        };
        // GRID COMMON TYPE EXTENSIONS
        // ============

        $.fn.extend({
            _bgAria: function (name, value) {
                return (value) ? this.attr("aria-" + name, value) : this.attr("aria-" + name);
            },

            _bgBusyAria: function (busy) {
                return (busy == null || busy) ?
                    this._bgAria("busy", "true") :
                    this._bgAria("busy", "false");
            },

            _bgRemoveAria: function (name) {
                return this.removeAttr("aria-" + name);
            },

            _bgEnableAria: function (enable) {
                return (enable == null || enable) ?
                    this.removeClass("disabled")._bgAria("disabled", "false") :
                    this.addClass("disabled")._bgAria("disabled", "true");
            },

            _bgEnableField: function (enable) {
                return (enable == null || enable) ?
                    this.removeAttr("disabled") :
                    this.attr("disabled", "disable");
            },

            _bgShowAria: function (show) {
                return (show == null || show) ?
                    this.show()._bgAria("hidden", "false") :
                    this.hide()._bgAria("hidden", "true");
            },

            _bgSelectAria: function (select) {
                return (select == null || select) ?
                    this.addClass("active")._bgAria("selected", "true") :
                    this.removeClass("active")._bgAria("selected", "false");
            },

            _bgId: function (id) {
                return (id) ? this.attr("id", id) : this.attr("id");
            }
        });

        if (!String.prototype.resolve) {
            var formatter = {
                "checked": function (value) {
                    if (typeof value === "boolean") {
                        return (value) ? "checked=\"checked\"" : "";
                    }
                    return value;
                }
            };

            String.prototype.resolve = function (substitutes, prefixes) {
                var result = this;
                $.each(substitutes, function (key, value) {
                    if (value != null && typeof value !== "function") {
                        if (typeof value === "object") {
                            var keys = (prefixes) ? $.extend([], prefixes) : [];
                            keys.push(key);
                            result = result.resolve(value, keys) + "";
                        }
                        else {
                            if (formatter && formatter[key] && typeof formatter[key] === "function") {
                                value = formatter[key](value);
                            }
                            key = (prefixes) ? prefixes.join(".") + "." + key : key;
                            var pattern = new RegExp("\\{\\{" + key + "\\}\\}", "gm");
                            result = result.replace(pattern, (value.replace) ? value.replace(/\$/gi, "&#36;") : value);
                        }
                    }
                });
                return result;
            };
        }

        if (!Array.prototype.first) {
            Array.prototype.first = function (condition) {
                for (var i = 0; i < this.length; i++) {
                    var item = this[i];
                    if (condition(item)) {
                        return item;
                    }
                }
                return null;
            };
        }

        if (!Array.prototype.contains) {
            Array.prototype.contains = function (condition) {
                for (var i = 0; i < this.length; i++) {
                    var item = this[i];
                    if (condition(item)) {
                        return true;
                    }
                }
                return false;
            };
        }

        if (!Array.prototype.page) {
            Array.prototype.page = function (page, size) {
                var skip = (page - 1) * size,
                    end = skip + size;
                return (this.length > skip) ?
                    (this.length > end) ? this.slice(skip, end) :
                        this.slice(skip) : [];
            };
        }

        if (!Array.prototype.where) {
            Array.prototype.where = function (condition) {
                var result = [];
                for (var i = 0; i < this.length; i++) {
                    var item = this[i];
                    if (condition(item)) {
                        result.push(item);
                    }
                }
                return result;
            };
        }

        if (!Array.prototype.propValues) {
            Array.prototype.propValues = function (propName) {
                var result = [];
                for (var i = 0; i < this.length; i++) {
                    result.push(this[i][propName]);
                }
                return result;
            };
        }

        // GRID PLUGIN DEFINITION
        // =====================

        var old = $.fn.bootgrid;

        $.fn.bootgrid = function (option) {
            var args = Array.prototype.slice.call(arguments, 1),
                returnValue = null,
                elements = this.each(function (index) {
                    var $this = $(this),
                        instance = $this.data(namespace),
                        options = typeof option === "object" && option;

                    if (!instance && option === "destroy") {
                        return;
                    }
                    if (!instance) {
                        $this.data(namespace, (instance = new Grid(this, options)));
                        init.call(instance);
                    }
                    if (typeof option === "string") {
                        if (option.indexOf("get") === 0 && index === 0) {
                            returnValue = instance[option].apply(instance, args);
                        }
                        else if (option.indexOf("get") !== 0) {
                            return instance[option].apply(instance, args);
                        }
                    }
                });
            return (typeof option === "string" && option.indexOf("get") === 0) ? returnValue : elements;
        };

        $.fn.bootgrid.Constructor = Grid;

        // GRID NO CONFLICT
        // ===============

        $.fn.bootgrid.noConflict = function () {
            $.fn.bootgrid = old;
            return this;
        };

        // GRID DATA-API
        // ============

        $("[data-toggle=\"bootgrid\"]").bootgrid();
//    })(jQuery);
}));