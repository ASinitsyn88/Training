// Если браузер поддерживает Promise (асинхронную обработку)
var canPromise = !!window.Promise;
if (canPromise) {
  // Обработчик результата cadesplugin - resolve
  cadesplugin.then(function() {
    Common_CheckForPlugIn(); // эта функция не вызывается
  },
  // Обработчик результата cadesplugin - reject
  function(error) {
    alert("Необходимо установить CryptoPro Browser Plug-in");
  });
};

function Common_CheckForPlugIn() {
    cadesplugin.set_log_level(cadesplugin.LOG_LEVEL_DEBUG);
    var canAsync = !!cadesplugin.CreateObjectAsync;
    if (canAsync) {
      include_async_code().then(function() {
        return CheckForPlugIn_Async();
      });
    } else {
      alert("Отсутствует или отключён CryptoPro Browser Plug-in");
    }
}
