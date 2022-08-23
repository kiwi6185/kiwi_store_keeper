$(document).ready(function() {
    $('#list').click(
        function(event){
            event.preventDefault();
            $('#products').addClass('list-group-item');
            $('.item').addClass('list-group-item');
            $('.item').addClass('col-md-12');
        }
    );
    $('#grid').click(
        function(event){
            event.preventDefault();
            $('#products').removeClass('list-group-item');
            $('.item').removeClass('list-group-item');
            $('.item').removeClass('col-md-12');
            $('#products').addClass('grid-group-item');
            $('.item').addClass('grid-group-item');
            $('.item').addClass('col-md-4');
        }
    );
});