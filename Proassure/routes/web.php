<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/



Route::get('call', array('as' => 'call','uses' => 'OTController@gotoCall',));
Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');
Route::resource('expert','ExpertController');
Route::resource('garagiste','GaragisteController');
Route::resource('remorqueur','RemorqueurController');
Route::resource('client','ClientController');
Route::resource('utilisateur','UtilisateurController');
Route::resource('contrat','ContratController');

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');
