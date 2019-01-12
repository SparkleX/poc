sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/json/JSONModel',
		'sap/m/MessageToast'
	], function(jQuery, Controller, JSONModel, MessageToast) {
	"use strict";

	var PageController = Controller.extend("help.App", {

		onInit: function () {
			var view = this.getView();
			var oModel = new JSONModel({
				Id: -1,
				ItemCode:"123",
				ItemName:"aaa"
			});
			view.setModel(oModel);
			sap.ui.getCore().setModel(oModel);
		},
		onDelete: function (evt) 
		{

		},
		onUpdate: function (evt) 
		{
			var oView = this.getView();
			var itenName = oView.byId("id3").getValue();
			var oModel = oView.getModel();
			var data = oModel.getData();
			MessageToast.show(evt.getSource().getId() + path);
		}
		
	});

	return PageController;
});