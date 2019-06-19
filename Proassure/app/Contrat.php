<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Contrat extends Model
{
    /**
     * The database table used by the model.
     *
     * @var string
     */
    protected $table = 'contrat';

    /**
    * The database primary key value.
    *
    * @var string
    */
    protected $primaryKey = 'id';

    /**
     * Attributes that should be mass-assignable.
     *
     * @var array
     */
    protected $fillable = ['user_id', 'description', 'datedeb','datefin'];

    
}
