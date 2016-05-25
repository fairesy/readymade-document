var AJAX = (function(){
  function get(url){
    return $.ajax({
      method : 'GET',
      url : url,
    });
  }
  function post(url, data){
    return $.ajax({
      method : 'POST',
      url : url,
      data : data
    });
  }

  return {
    get : get,
    post : post
  }
})();
