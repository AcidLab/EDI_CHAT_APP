<html>
<head>
    <style>

        .OT_publisher, .OT_subscriber {
            left: 10px;
            z-index: 100;
            border: 3px solid white;
            border-radius: 3px;
            margin-bottom: 12px !important;
        }
    </style>
</head>
<body>
<!-- OpenTok.js library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://static.opentok.com/v2/js/opentok.js"></script>

<div style="margin-bottom: 20px">
    <label for="session">Session ID</label><input id="session" type="text" value="{{$sessionId}}">

    <button onclick="start()">START</button>
    <!--button onclick="startArchive()">START RECORDING</button>
    <button onclick="stopArchive()">STOP RECORDING</button-->
</div>
@if($archives)
    <div style="margin-bottom: 20px">
        @foreach($archives as $archive)
            <a href="{{$archive->url}}"><span>{{$archive->id}}</span></a><br>
        @endforeach
    </div>
@endif
<script>
    var archiveId;

    function start() {
        $.ajax({
            url: '/api/ot/token/',
            type: 'post',
            data: {
                'sessionId': $("#session").val()
            },
            success: function (response) {
                // credentials
                var apiKey = '46254462';
                var sessionId = response.sessionId;
                var token = response.token;

                // connect to session
                var session = OT.initSession(apiKey, sessionId);

                // create publisher
                var publisher = OT.initPublisher();
                session.connect(token, function (err) {
                    // publish publisher
                    session.publish(publisher);
                });

                // create subscriber
                session.on('streamCreated', function (event) {
                    session.subscribe(event.stream);
                });
            }

        });

    }

    function startArchive() {
        $.ajax({
            url: '/api/ot/archive/start',
            type: 'post',
            data: {
                'sessionId': $("#session").val()
            },
            success: function (response) {
                console.log(response);
                archiveId = response.id;
            }

        });
    }

    function stopArchive() {
        $.ajax({
            url: '/api/ot/archive/stop',
            type: 'post',
            data: {
                'archiveId': archiveId
            },
            success: function (response) {
                console.log(response);
            }

        });
    }


</script>

</body>

</html>