<?php
/**
 * Created by PhpStorm.
 * User: selim
 * Date: 1/22/2019
 * Time: 1:57 AM
 */

namespace App\Models;


use Illuminate\Database\Eloquent\Model;

class Session extends Model
{
    protected $table = 'sessions';

    protected $fillable = [
        'id', 'session_id', 'accepted'
    ];

    public $timestamps = true;

    public function archives(){
        return $this->hasMany(Archive::class);
    }

}