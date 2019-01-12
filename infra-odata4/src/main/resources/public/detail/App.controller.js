sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/odata/v4/ODataModel',
		'sap/m/MessageToast',
		"sap/ui/model/BindingMode"
	], function(jQuery, Controller, ODataModel, MessageToast,BindingMode) {
	"use strict";

	var PageController = Controller.extend("help.App", {

		onInit: function () {
			var view = this.getView();
			var oModel = view.getModel();


			view.bindElement("/BmoORDRs(-1)");//,{$expand:'RDR1'});

		},
		/*onReadSuccess: function(oData, oResponse)
		{
			var view = this.getView();
			var oModel = view.getModel();
			view.bindElement("/Items(-1)");
			MessageToast.show(oData);
             
        },   */       
		onDelete: function (evt) 
		{
			var oModel = this.getView().getModel();
			//oModel.remove("/Items(-1)");
			oModel.resetChanges();
			MessageToast.show(evt.getSource().getId() + " Pressed");
		},
		onUpdate: function (evt) 
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