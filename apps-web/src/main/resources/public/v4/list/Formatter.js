sap.ui.define([
    'sap/ui/model/odata/v4/ODataModel',
    'sap/ui/model/json/JSONModel'
], function (ODataModel, JSONModel) {
    "use strict";
    return    {
        statusText: function (id) {
            var data = "123";
            $.ajax({
                url: "/odata4/OCRDCollection("+id+")?$format=json",
                async: false,
                success: function (result) {
                    console.log(result);
                    data = result;
                },
                error: function (e) {
                    console.log(e.message);
                }
            });

            return data.cardCode;
        }
    };
});