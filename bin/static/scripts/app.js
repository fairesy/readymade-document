
var routes = {
  "/login" : {
    on : function(){
      console.log("login");
    }
  }
}

var router = Router(routes);
router.init("/");
