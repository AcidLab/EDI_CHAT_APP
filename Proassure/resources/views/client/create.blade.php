@extends('layouts.app')

@section('content')

<div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-12">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">Ajouter un client</h1>
              </div>
              <form class="client" method="post" action="{{ route('client.store') }}">
                {{ csrf_field() }}
                <div class="form-group row">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                    <input type="text" class="form-control-user form-control{{ $errors->has('name') ? ' is-invalid' : '' }}" name="name" value="{{ old('name') }}" required autofocus placeholder="Nom complet">

                    @if ($errors->has('name'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong style="color: red;">{{ $errors->first('name') }}</strong>
                                    </span>
                                @endif
                  </div>
                </div>
                <div class="form-group">
                  <input type="email" class="form-control-user form-control{{ $errors->has('email') ? ' is-invalid' : '' }}" name="email" value="{{ old('email') }}" required id="exampleInputEmail" placeholder="Adresse email">

                  @if ($errors->has('email'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong style="color: red;">{{ $errors->first('email') }}</strong>
                                    </span>
                                @endif

                </div>
                <div class="form-group row">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                    <input type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Password" name="password">

                    @if ($errors->has('password'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong style="color: red;">{{ $errors->first('password') }}</strong>
                                    </span>
                                @endif

                  </div>
                  
                </div>
                <input type="submit" class="btn btn-primary btn-user btn-block" value="Enregistrer">
                  
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
            

@endsection