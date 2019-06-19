<?php

namespace App\Http\Controllers;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use View;
use Auth;
use App\Contrat;
use Illuminate\Http\Request;
use Redirect;
use Illuminate\Support\Facades\Hash;

class ContratController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\View\View
     */
    public function index(Request $request)
    {
        $id = $request->input('id');
        $contrats = contrat::where('user_id',$id)->get();
        $view = View::make('contrat.index');
        $view -> contrats = $contrats;
        $view ->id = $id;
        return $view;
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        return view('contrat.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param \Illuminate\Http\Request $request
     *
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function store(request $request )
    {
        $contrat = Contrat::create([
            'user_id' => $request->input('id'),
            'description' => $request->input('description'),
            'datedeb' => $request->input('date_start'),
            'datefin' => $request->input('date_end')]);
        

        return Redirect::to(route('contrat.index').'?id='.$request->input('id'))->with('flash_message', 'contrat added!');
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     *
     * @return \Illuminate\View\View
     */
    public function show($id)
    {
        $contrat = contrat::find($id);

        return Redirect::to(route('contrat.index'));
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     *
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function destroy($id)
    {
        contrat::destroy($id);

        return redirectRedirect::to(route('contrat.index'))->with('flash_message', 'contrat added!');
    }
}
