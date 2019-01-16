sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/odata/v2/ODataModel',
		'sap/m/MessageToast',
		"sap/ui/model/BindingMode"
	], function(jQuery, Controller, JSONModel, MessageToast,BindingMode) {
	"use strict";

	var PageController = Controller.extend("help.App", {

		onInit: function () {
			var view = this.getView();
			var oModel = view.getModel();
			view.bindElement("/SalesOrders(-1)");

		},
     
		onDelete: function (evt) 
		{
			var oView = this.getView();
			var id = oView.byId("idId").getValue();
			var oModel = oView.getModel();
			//oModel.remove("/SalesOrders("+id+")");
			oView.getBindingContext().delete("$auto");
			MessageToast.show("Deleted");
		},
		onUpdate: function (evt) 
		{
			var oView = this.getView();
			var oModel = oView.getModel();
			oModel.submitBatch("group1");
			MessageToast.show("Updated");
		},
		onCreate: function (evt) 
		{
			
			var oView = this.getView();
			var oItem = oView.byId("id2");
			//var itenName = oView.byId("id3").getValue();
			
			var oModel = oView.getModel();
			var bindingContext = oView.getBindingContext();
			var path = bindingContext.getPath();
			var oData = bindingContext.getObject();
			//oModel.update(path, oData);
			
			oModel.submitBatch("group1");
			MessageToast.show(evt.getSource().getId());
		}
		
	});

	return PageController;
});