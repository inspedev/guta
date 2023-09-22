$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();
  var actions = $("table td:last-child").html();
  // Append table with add row form on add new button click
  $(".add-new").click(function(){
    $(this).attr("disabled", "disabled");
    var index = $("table tbody tr:last-child").index();
    let robot_name = 'robot-' + document.getElementById("robot-v").value + "-name";
    let robot_weight = 'robot-' + document.getElementById("robot-v").value + "-value";
    var row = '<tr class="select">' +
      '<td><input type="text" class="form-control disabled" name="loginp" id="loginp" value="'+ document.getElementById("login-v").value+'"></td>' +
      '<td><input type="text" class="form-control disabled" name="namep" id="namep" value="'+ document.getElementById(robot_name).value+'"></td>' +
      '<td><select name="time"> <option value="6:00 - 14:00">6:00 - 14:00 </option> <option value="14:00 - 22:00">14:00 - 22:00</option> <option value="22:00 - 6:00">22:00 - 6:00</option> </select></td>' +
      '<td><input type="number" class="form-control disabled" name="weight" id="weight" value="'+ document.getElementById(robot_weight).value+'"></td>' +
      '<td><input type="number" class="form-control disabled" name="full" id="full"></td>' +
      '<td><input type="number" class="form-control" name="count" id="count"></td>' +
      '<td><input type="text" class="form-control" name="text" id="text"></td>' +
      '<input type="hidden" name="robot_v" id="robot_v" value="'+ document.getElementById("robot-v").value+'">' +
      '<td>' + actions + '</td>' +
      '</tr>';
    $("table").append(row);
    $("table tbody tr").eq(index + 1).find(".add, .edit").toggle();
    $('[data-toggle="tooltip"]').tooltip();
    window.scrollTo(0, document.body.scrollHeight);
  });
  // Add row on add button click
  $(document).on("click", ".add", function(){
/*    var empty = false;
    var input = $(this).parents("tr").find('input[type="text"]');
    input.each(function(){
      if(!$(this).val()){
        $(this).addClass("error");
        empty = true;
      } else{
        $(this).removeClass("error");
      }
    });
    $(this).parents("tr").find(".error").first().focus();
    if(!empty){
      input.each(function(){
        $(this).parent("td").html($(this).val());
      });
      $(this).parents("tr").find(".add, .edit").toggle();
      $(".add-new").removeAttr("disabled");
    }*/
  });
});

function submitForm() {
  localStorage.setItem("sessionGuta", 1);
  document.getElementById('form').submit()
}