@extends('layouts.app')

@section('content')
<div class="card shadow mb-4">

	@if (count($contrats)> 0)

	<div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Liste des contrats</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                    <th>id contrat</th>
                      <th>Intitulé</th>
                      <th>Date debut</th>
                      <th>Date debut</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  <tbody>

                  	@foreach($contrats as $contrat)
                    <tr>
                      <td>{{$contrat->id}}</td>
                      <td>{{$contrat->description}}</td>
                      <td>{{$contrat->datedeb}}</td>
                      <td>{{$contrat->datefin}}</td>
                      <td>
                      	<form method="DELETE" action="{{route('contrat.destroy',$contrat->id)}}">
                      		{{ csrf_field() }}
                             <button type="submit" class="btn btn-danger">Supprimer</button>
                      	</form>
                                    
                      </td>
                    </tr>
                    @endforeach
    
                  </tbody>
                </table>
              </div>
            </div>
          </div>

	@else

	<div class="card-header py-3">

              <h6 class="m-0 font-weight-bold text-primary">Aucun contrat</h6>
              <button class="btn btn-primary" style="margin-top: 20px;" data-toggle="modal" data-target="#addcontract">Ajouter un contrat</button>
            </div>
          </div>

	@endif


  <div class="modal fade" id="addcontract" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Ajouter un contrat</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form method="POST" action="{{route('contrat.store')}}">
          {{ csrf_field() }}
          <input type="hidden" name="id" value="{{$id}}">
                <div class="form-group row">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                    <input type="text" name="description"  required autofocus placeholder="Intitulé" class="form-control">
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                    <input type="date" name="date_start"  required autofocus placeholder="Date debut" class="form-control">
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                    <input type="date" name="date_end"  required autofocus placeholder="Date fin" class="form-control">
                  </div>
                </div>

                </div>
      <div class="modal-footer">
        <button type="cancel" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
        <button type="submit" class="btn btn-primary">Enregistrer</button>
      </div>
        </form>
      
    </div>
  </div>
</div>
            

@endsection