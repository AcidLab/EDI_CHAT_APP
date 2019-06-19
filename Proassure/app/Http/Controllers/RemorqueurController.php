<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use View;
use App\User;
use Redirect;
use Illuminate\Support\Facades\Hash;

class RemorqueurController extends Controller
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
        $remorqueurs = User::where('type',3)->get();

        $view = View::make('remorqueur.index');
        $view -> remorqueurs = $remorqueurs;
        return $view;
    }

    public function create()
    {

        $view = View::make('remorqueur.create');
        return $view;
    }

    public function store (Request $request) {

        $user = User::create([
            'name' => $request->input('name'),
            'email' => $request->input('email'),
            'type' => 3,
            'password' => Hash::make($request->input('password')),
        ]);

        return Redirect::to(route('remorqueur.index'));

    }

    public function show($id) {

        $user = User::find($id);
        $user->delete();

        return Redirect::to(route('remorqueur.index'));
    }

    public function destroy ($id) {

        $user = User::find($id);
        $user->delete();

        return Redirect::to(route('remorqueur.index'));

    }
}
