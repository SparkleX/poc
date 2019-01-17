sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/odata/v4/ODataModel',
		'sap/m/MessageToast',
		"sap/ui/model/BindingMode"
	], function(jQuery, Controller, DataModel, MessageToast,BindingMode) {
	"use strict";

	var PageController = Controller.extend("help.App", {

		onInit: function () {
		},
		//Load new entry before update
		onLoad: function () {
			var oView = this.getView();
			var id = oView.byId("idId").getValue();
			oView.bindElement("/ORDRCollection("+id+")");
		},		
		onUpdate: function (evt) 
		{
			var oView = this.getView();
			var oModel = oView.getModel();
			oModel.submitBatch("group1");
			MessageToast.show("Updated");
		},
		//New Blank before create
		onNew: function (evt) 
		{
			var oView = this.getView();
			var oModel = oView.getModel();			
			var listBinding = oModel.bindList("/ORDRCollection");
			var oCreate = listBinding.create({bpId:3});
			oView.setBindingContext(oCreate)
			//var oContext = oCreate.created();
		},
		onCreate: function (evt) 
		{
			var oView = this.getView();
			var oModel = oView.getModel();
			oModel.submitBatch("group1");
		},
		onDelete: function (evt) 
		{
			var oView = this.getView();
			oView.getBindingContext().delete("$auto");
			MessageToast.show("Deleted");
		}
		
	});

	return PageController;
});