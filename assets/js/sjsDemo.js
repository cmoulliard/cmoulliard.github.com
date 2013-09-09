$(document).ready(function() {
    $('.search-field').simpleJekyllSearch({
        searchResults:'.results',
        template : '<a href="{url}" title="{title}" class="fly-in-right">{title}</a><br/>',
        dataType : 'html'
    });
});