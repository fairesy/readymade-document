var routes = {
		"/documents/:documentId/new" : function(documentId){
			InputSender.init();
			Filter.init();
		}
}

var router = Router(routes).configure({
	  html5history: true,
	  convert_hash_in_init: false
	});

router.init("/");