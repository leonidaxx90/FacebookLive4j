Generate never expired token

https://graph.facebook.com/v2.8/oauth/access_token?grant_type=fb_exchange_token&client_id={appId}&client_secret={appSecret}&fb_exchange_token={Token accesso dell'utente} --> return 2 hours exipred token

https://graph.facebook.com/v2.8/me?access_token={token ritornato nel passo precedente} --> return account id

https://graph.facebook.com/v2.8/{account id ritornato dal passo precedente}/accounts?access_token={access token ritornato 2 passi prima} --> return a never expired token. Test it on https://developers.facebook.com/tools/debug/accesstoken and check it is never expired
