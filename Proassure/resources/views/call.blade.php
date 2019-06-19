<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Pro assure - Login</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-default" style="background-color: #dbdcdb">

  <div class="mt-5 ml-5 mr-5 mb-5">

    <!-- Outer Row -->
    <div class="row">

      <div class="col-md-2">


        <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Intervenants</h6>
                </div>
                <div class="card-body">

                  <h6 class="m-0 font-weight-bold text-black mb-2">Experts</h6>

                  @foreach ($experts as $user)

                  <a href="#" onclick="addUser()" class="btn btn-success btn-icon-split text-left mb-1" style="width: 100%;text-align: left !important;">
                    <span class="icon text-white" style="text-align: left !important;margin-left: -5px;">
                      <i class="fas fa-phone"></i>
                    </span>
                    <span class="text" style="text-align: left !important;">{{$user->name}}</span>
                  </a>

                  @endforeach

                  <h6 class="m-0 font-weight-bold text-black mb-2">Garagistes</h6>

                  @foreach ($garagistes as $user)

                  <a href="#" class="btn btn-success btn-icon-split text-left mb-1" style="width: 100%;text-align: left !important;">
                    <span class="icon text-white" style="text-align: left !important;margin-left: -5px;">
                      <i class="fas fa-phone"></i>
                    </span>
                    <span class="text" style="text-align: left !important;">{{$user->name}}</span>
                  </a>

                  @endforeach

                  <h6 class="m-0 font-weight-bold text-black mb-2">Remorqueurs</h6>

                  @foreach ($remorqueurs as $user)

                  <a href="#" class="btn btn-success btn-icon-split text-left mb-1" style="width: 100%;text-align: left !important;">
                    <span class="icon text-white" style="text-align: left !important;margin-left: -5px;">
                      <i class="fas fa-phone"></i>
                    </span>
                    <span class="text" style="text-align: left !important;">{{$user->name}}</span>
                  </a>

                  @endforeach

                  
                  
                </div>
              </div>
        

      </div>

      <div class="col-md-6">

        <div class="card shadow mb-4" style="height: 500px;">
                
                <div class="card-body" id="ot-call">
                  
                </div>

                <div class="card-footer">
                  <center>
                  <button class="btn btn-danger" onclick="close_window()">Raccrocher</button>
                  </center>
                </div>
              </div>
      </div>

      <div class="col-md-4" style="width: 100%;">

        <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Prédéclaration</h6>
                </div>
                <div class="card-body">
                  <form>
                    <input type="text" class="form-control" name="">
                  </form>
                </div>
                <div class="card-footer">

                  <button class="btn btn-primary">Enregistrer</button>
                  
                </div>
              </div>
        

      </div>

    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>
  <script src="https://static.opentok.com/v2/js/opentok.js"></script>

  <script>
      $(document).ready(function () {
          let sessionId = '{{$sessionId}}';
          if(sessionId!=='')
              start(sessionId);
      });

      function addUser() {
          let sessionId = '{{$sessionId}}';

          $.ajax({
              url: '/api/ot/adduser/',
              type: 'post',
              data: {
                  'sessionId': sessionId,
              },
              success: function (response) {
                  // credentials

              }

          });
      }
      function start(sessionId) {
          $.ajax({
              url: '/api/ot/token/',
              type: 'post',
              data: {
                  'sessionId': sessionId,
                  'accepted': 1
              },
              success: function (response) {
                  // credentials
                  var apiKey = '46254462';
                  var sessionId = response.sessionId;
                  var token = response.token;

                  // connect to session
                  var session = OT.initSession(apiKey, sessionId);

                  // create publisher
                  var publisherOptions = {
                      insertMode: 'append',
                  };
                  var publisher = OT.initPublisher('ot-call', publisherOptions);
                  session.connect(token, function (err) {
                      // publish publisher
                      session.publish(publisher);
                  });

                  // create subscriber
                  session.on('streamCreated', function (event) {
                      var options = {insertMode: 'append'}
                      session.subscribe(event.stream, 'ot-call', options);
                  });

                  $("#status").html("In Call");
                  $("#close").modal('show');

              }

          });
      }

      function close_window() {
          if (confirm("End Call?")) {
              window.location.replace("/home");
          }
      }

  </script>
</body>

</html>
