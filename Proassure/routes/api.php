<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::group(['middleware' => 'api'], function() {
    Route::post('/ot/session', 'OTController@requestSessionId');
    Route::post('/ot/token', 'OTController@generateToken');
    Route::post('/ot/archive/start', 'OTController@startArchive');
    Route::post('/ot/archive/stop', 'OTController@stopArchive');
    Route::post('/ot/archive/', 'OTController@getArchive');
    Route::post('/ot/adduser/', 'OTController@addUser');

    Route::get('/ot/session', 'OTController@requestSessionId');
    Route::get('/ot/token', 'OTController@generateToken');
    Route::get('/ot/archive/start', 'OTController@startArchive');
    Route::get('/ot/archive/stop', 'OTController@stopArchive');
    Route::get('/ot/archive/', 'OTController@getArchive');
    Route::get('/ot/adduser/', 'OTController@addUser');
});
