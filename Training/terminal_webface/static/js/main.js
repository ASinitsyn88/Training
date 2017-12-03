$(function() {

    $('.js-input, .js-select').each(checkNotEmpty);
    $('.js-input, .js-select').change(checkNotEmpty);

    function checkNotEmpty() {
        if ($(this).val() != '') {
            $(this).addClass('b-input_not-empty');
        } else {
            $(this).removeClass('b-input_not-empty');
        }
    };
});
