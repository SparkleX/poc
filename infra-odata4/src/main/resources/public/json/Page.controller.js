sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/odata/v2/ODataModel'
	], function(jQuery, Controller, JSONModel) {
	"use strict";

	var PageController = Controller.extend("sap.m.sample.Select2Columns.Page", {

		onInit: function () {
			var oModel = new sap.ui.model.odata.v2.ODataModel({serviceUrl:"http://localhost:8080/test/",mParameters:
			{
				useBatch:false
			}});
			this.getView().setModel(oModel);
		}
	});

	return PageController;
});