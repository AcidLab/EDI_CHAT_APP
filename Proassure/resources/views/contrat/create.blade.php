@extends('layouts.app')

@section('content')

<div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-12">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">Ajouter un contrat</h1>
              </div>
              <form class="contrat" method="post" action="{{ route('contrat.store') }}">
                {{ csrf_field() }}
                <div class="form-group row">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                    <input type="text" name="Description"  required autofocus placeholder="Description">
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                    <input type="text" name="Description"  required autofocus placeholder="datedeb">
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                    <input type="text" name="datefin"  required autofocus placeholder="datefin">
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