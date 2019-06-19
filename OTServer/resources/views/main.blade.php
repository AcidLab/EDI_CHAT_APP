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
<script src="https://js.pusher.com/4.3/pusher.min.js"></script>

<div style="margin-bottom: 20px">
    <span id="status">Waiting for connection requests..</span>
    <button id="close" onclick="window.location.reload()" hidden>End Call</button>

    @if(sizeof($sessions) > 0)
        <h4 style="margin-top: 20px; margin-bottom: 10px">Pending connections</h4>
        <div id="connections">
            @foreach($sessions as $session)
                <div>
                    <a onclick="start('{{$session->session_id}}')" href="#">Session #{{$session->id}}
                        at {{$session->created_at}}</a>
                </div>
            @endforeach
        </div>
    @endif

</div>

<script>

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
                var publisher = OT.initPublisher();
                session.connect(token, function (err) {
                    // publish publisher
                    session.publish(publisher);
                });

                // create subscriber
                session.on('streamCreated', function (event) {
                    session.subscribe(event.stream);
                });

                $("#status").html("In Call");
                $("#close").show();

            }

        });

    }


    $(document).ready(function () {
        Pusher.logToConsole = true;

        let pusher = new Pusher('ab501e6a821b06d9c651', {
            cluster: 'eu',
            forceTLS: true
        });

        let channel = pusher.subscribe('threads');
        channel.bind('new-call', function (data) {
            let holder = $("#connections");
            let newElement = document.createElement('div');
            let newElementA = document.createElement('a');
            newElementA.setAttribute("onclick", "start('" + data['session_id'] + "')");
            newElementA.setAttribute("href", "#");
            newElementA.innerHTML = "Session #" + data['id'] + " (Now)";
            newElement.appendChild(newElementA);
            holder.prepend(newElement);
            alert('New Call Request!');
        });
    });

</script>

</body>

</html>