@extends('layouts.app')

@section('content')
<div class="card shadow mb-4">

	@if (count($garagistes)> 0)

	<div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Liste des garagistes</h6>
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

                  	@foreach($garagistes as $user)
                    <tr>
                      <td>{{$user->name}}</td>
                      <td>{{$user->email}}</td>
                      <td>
                      	<form method="DELETE" action="{{route('garagiste.destroy',$user->id)}}">
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
              <h6 class="m-0 font-weight-bold text-primary">Aucun garagiste</h6>
            </div>
          </div>

	@endif
            

@endsection