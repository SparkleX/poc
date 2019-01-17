sap.ui.define([
		'jquery.sap.global',
		'sap/ui/core/mvc/Controller',
		'sap/ui/model/odata/v4/ODataModel',
		'sap/m/MessageToast',
		"sap/ui/model/BindingMode",
		"sap/ui/core/routing/History"
	], function(jQuery, Controller, DataModel, MessageToast,BindingMode,History) {
	"use strict";

	var PageController = Controller.extend("help.controller.Detail", {

		addMode: false,
		onInit: function () {
		var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.getRoute("detail").attachPatternMatched(this._onObjectMatched, this);
		},
		_onObjectMatched: function (oEvent) {
			var id = oEvent.getParameter("arguments").id;
			if(id.endsWith("(-1)"))	{
				this.onNew(oEvent);
				this.addMode = true;
				return;
			}
			this.getView().bindElement("/"+id);
		},
		onSave: function () {
			if(this.addMode)
			{
				this.onCreate();
			}
			else
			{
				this.onUpdate();
			}
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
			this.onNavBack();
		},
		onAction : function (oEvent) 
		{
			var oView = this.getView();
			var oItem = oView.byId("idFunction");
			var oModel = oView.getModel();
			oItem.invalidate()
			oModel.refresh("$auto");
			/*var oBinding = oModel.bindContext("/function(...)");//oModel.createBindingContext("/function");
			var oMetaModel = oBinding.getModel();
    		//oContext.setParameter("Comment", sComment);
    		oBinding.execute()
    		.then(function(){
    			var oBind2 = oBinding;
    			var oModel2 = oModel;
    			oView.bindElement("/ORDRCollection(-8)");
			},function () {
				alert('a');
			}).catch(function(e){
				alert(e);
 			});*/
		},
		onNavBack: function () {
			var oHistory = History.getInstance();
			var sPreviousHash = oHistory.getPreviousHash();

			if (sPreviousHash !== undefined) {
				window.history.go(-1);
			} else {
				var oRouter = UIComponent.getRouterFor(this);
				oRouter.navTo("list", {}, true);
			}
		}
	});

	return PageController;
});