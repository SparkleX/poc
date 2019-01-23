sap.ui.define([], function () {
	"use strict";
	return {
		statusText: function (id) {
            return "GET" + id;
		}
	};
});