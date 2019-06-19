<?php
/**
 * Created by PhpStorm.
 * User: selim
 * Date: 2/14/2019
 * Time: 4:19 PM
 */

namespace App\Repositories;

use App\User;

class UserRepository extends ResourceRepository
{
    public function __construct(User $user)
    {
        $this->model = $user;
    }

    public function getByType($type)
    {
        return $this->model->where('type', $type)->get();
    }

}