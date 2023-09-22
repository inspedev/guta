$(document).ready(function () {
  let session = localStorage.getItem("sessionGuta");
  if (session && session == 1) {
    localStorage.setItem("sessionGuta", 0);
    document.getElementById("login").classList.toggle("hidden");
    document.getElementById("main-head").value = 'Режим перегляду';
    document.getElementById("content").classList.toggle("hidden");
    document.getElementById("button").classList.toggle("hidden");
    setTimeout("location.reload(true);", 10000);
  }
});

function login() {
  let login = document.getElementById("time").value;
  let robot = document.getElementById("robot").value;
  let password = document.getElementById("password").value;
  if (
    login == 1 && password == 1 && robot == 1 ||
    login == 2 && password == 2 && robot == 1 ||
    login == 3 && password == 3 && robot == 1 ||
    login == 4 && password == 4 && robot == 1 ||
    login == 1 && password == 5 && robot == 2 ||
    login == 2 && password == 6 && robot == 2 ||
    login == 3 && password == 7 && robot == 2 ||
    login == 4 && password == 8 && robot == 2
  ) {
    document.getElementById("login").classList.toggle("hidden");
    document.getElementById("login-v").value = 'бригада №' + login + ' робот №' + robot;
    document.getElementById("robot-v").value = robot;
    document.getElementById("content").classList.toggle("hidden");
    document.getElementById("head").innerText = login;
    setTimeout("location.reload(true);", 120000);
  }

}