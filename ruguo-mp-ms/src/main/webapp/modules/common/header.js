define(['jquery', 'bootstrap'], function () {
    $(function () {
        $("a.fmenu").click(function (event) {
            event.preventDefault();
            $(this).siblings("ul.nav").slideToggle(100);
            var $span = $(this).find("span.glyphicon");
            var classRight = "glyphicon-menu-right";
            var calssDown = "glyphicon-menu-down";
            if ($(this).find("span.glyphicon.glyphicon-menu-down").length !== 0) {
                $span.removeClass(calssDown);
                $span.addClass(classRight);
            } else {
                $span.removeClass(classRight);
                $span.addClass(calssDown);
            }
        });

        $(".bs-docs-sidenav .nav a").click(function (event) {
            event.preventDefault();
            //alert($(this).text() + ' ' + $(this).attr("href"));
            var href = $(this).attr("href");
            var pid = $(this).attr("data-menupid");
            window.location = webAppPath + href;
        });

        $("#to_userinfo").click(function () {
            window.location = webAppPath + "/personal/PersonAffair/index";
        });

        $("#to_password").click(function () {
            window.location = webAppPath + "/personal/password/index";

        });

        $("#to_logout").click(function () {
            window.location = webAppPath + "/logout";
        });

        function linkTohome() {
            window.location = getURI(webAppPath + "/a/sys/sysinit.do?method=init");
        };

        //导航右侧下拉选项自适应宽度
        $(".navbar-content-sy .navbar-nav .dropdown .dropdown-menu").css({
            "min-width": function () {
                var prevwidth = ($(this).closest('li.dropdown').width() + 2) + "px";
                return prevwidth;
            }
        });
    });
});