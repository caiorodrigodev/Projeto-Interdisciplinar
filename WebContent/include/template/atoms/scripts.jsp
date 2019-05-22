<!-- jQuery CDN - Slim version (=without AJAX) -->
<script src="node_modules/bootstrap/js/jquery.js"></script>

<!-- jQuery Input Mask -->
<script src="include/js/jquery.mask.min.js"></script>

<!-- Custom Mask for my template -->
<script src="include/js/custom.mask.js"></script>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="node_modules/popper.js/dist/umd/popper.js"></script>
<script src="node_modules/bootstrap/dist/js/bootstrap.js"></script>

<!-- jQuery Custom Scroller CDN -->
<script src="node_modules/bootstrap/js/scrollbar.js"></script>

<script type="text/javascript">
$(document).ready(function () {
    $("#sidebar").mCustomScrollbar({
        theme: "minimal"
    });

    $('#dismiss, .overlay').on('click', function () {
        $('#sidebar').removeClass('active');
        $('.overlay').removeClass('active');
    });

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').addClass('active');
        $('.overlay').addClass('active');
        $('.collapse.in').toggleClass('in');
        $('a[aria-expanded=true]').attr('aria-expanded', 'false');
    });
});
</script>