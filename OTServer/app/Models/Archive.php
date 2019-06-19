<?php
/**
 * Created by PhpStorm.
 * User: selim
 * Date: 1/22/2019
 * Time: 1:57 AM
 */

namespace App\Models;


use Illuminate\Database\Eloquent\Model;

class Archive extends Model
{
    protected $table = 'archives';

    protected $fillable = [
        'id', 'session_id', 'archive_id', 'url'
    ];

    public $timestamps = true;

    public function session(){
        return $this->belongsTo(Session::class, 'session_id');
    }

}