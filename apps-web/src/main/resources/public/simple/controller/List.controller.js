sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/odata/v4/ODataModel'
	], function(jQuery, Controller, DataModel) {
	"use strict";
	var ListController = Controller.extend("help.controller.List", {
		onInit : function (evt) {
		},
		onPress: function (oEvent) {
		
			var oItem = oEvent.getSource();
			var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.navTo("detail",{
				id: oItem.getBindingContext().getPath().substr(1)
			});
		},
		onNew: function (evt) 
		{
			var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.navTo("detail",{
				id: "ORDRCollection(-1)"
			});			
		},		
	});


	return ListController;

});