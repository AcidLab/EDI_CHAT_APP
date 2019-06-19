<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use View;
use App\User;
use Redirect;
use Illuminate\Support\Facades\Hash;

class UtilisateurController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index()
    {
        $utilisateurs = User::where('type',0)->get();

        $view = View::make('utilisateur.index');
        $view -> utilisateurs = $utilisateurs;
        return $view;
    }

    public function create()
    {

        $view = View::make('utilisateur.create');
        return $view;
    }

    public function store (Request $request) {

        $user = User::create([
            'name' => $request->input('name'),
            'email' => $request->input('email'),
            'type' => 0,
            'password' => Hash::make($request->input('password')),
        ]);

        return Redirect::to(route('utilisateur.index'));

    }

    public function show($id) {

        $user = User::find($id);
        $user->delete();

        return Redirect::to(route('utilisateur.index'));
    }

    public function destroy ($id) {

        $user = User::find($id);
        $user->delete();

        return Redirect::to(route('utilisateur.index'));

    }
}
