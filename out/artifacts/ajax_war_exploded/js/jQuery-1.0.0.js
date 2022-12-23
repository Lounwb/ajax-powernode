function jQuery(selector) {

    if (typeof selector == "string") {
        if (selector.charAt(0) == "#") {

            domObj = document.getElementById(selector.substring(1))

            return new jQuery()
        }
    }else if (typeof selector == "function") {
        window.onload = selector
    }

    this.html = function (htmlStr){
        domObj.innerHTML = htmlStr
    }
    this.click = function (func) {
        domObj.onclick = func
    }
    this.focus = function (func){
        domObj.onfocus = func
    }
    this.blur = function (func){
        domObj.onblur = func
    }
    this.change = function (func){
        domObj.onchange = func
    }

    this.val = function (v){
        if (v == undefined) {
            return domObj.value
        }
        domObj.value = v
    }
    /**
     * @param 请求的方式type GET/POST
     * @param 请求的url
     * @param 请求时提交的数据data
     * @param 请求时发送请求是同步还是异步async:true表示异步，false表示同步
     */
    jQuery.ajax = function (jsonArgs) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if(this.readyState == 4){
                if (this.status == 200) {
                    var jsonObj = JSON.parse(this.responseText);

                   jsonArgs.success(jsonObj)
                }else {
                    alert(this.status)
                }
            }
        }
        if (jsonArgs.type.toUpperCase() == "POST") {
            xhr.open("POST", jsonArgs.url, jsonArgs.async)

            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")

            xhr.send(jsonArgs.data)
        }
        if (jsonArgs.type.toUpperCase() == "GET") {

            xhr.open("GET", jsonArgs.url + "?" + jsonArgs.data, jsonArgs.async)

            xhr.send()
        }
    }
}

$ = jQuery

new jQuery()