const dishAjaxUrl = "admin/dishes/";
// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: dishAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: dishAjaxUrl + "filter",
            data: $("#filter").serialize()
        }).done(updateTableByData);
    }
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(dishAjaxUrl, updateTableByData);
}

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "day"
                },
                {
                    "data": "restaurant"
                },
                {
                    "data": "name"
                },
                {
                    "data": "price"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        })
    );
});