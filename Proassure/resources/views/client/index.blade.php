@extends('layouts.app')

@section('content')
<div class="card shadow mb-4">


	@if (count($clients)> 0)

	<div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Liste des clients</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Nom complet</th>
                      <th>Email</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>Nom complet</th>
                      <th>Email</th>
                      <th>Actions</th>
                    </tr>
                  </tfoot>
                  <tbody>

                  	@foreach($clients as $client)
                    <tr>
                      <td>{{$client->name}}</td>
                      <td>{{$client->email}}</td>
                      <td>
                      	<div class="row">

                          <div class="col-md-2">
                           
                        <form method="GET" action="{{route('contrat.index')}}">
                          <input hidden value="{{$client->id}}" name="id">
                        <button type="submit" class="btn btn-secondary">Contrats</button> 
                      </form>
                         </div>

                         
                         <div class="col-md-2">
                           <form method="DELETE" action="{{route('client.destroy',$client->id)}}">
                          {{ csrf_field() }}
                             <button type="submit" class="btn btn-danger">Supprimer</button>
                        </form>
                        
                         </div> 

                          

                        </div>           
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
              <h6 class="m-0 font-weight-bold text-primary">Aucun client</h6>
            </div>
          </div>

	@endif
            

@endsection