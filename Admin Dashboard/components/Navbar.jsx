import React from 'react';
import { NavLink } from 'react-router-dom';
import logo from '../assets/images/sport-training-program-line-icon-fitness-vector-19222898.jpg';

const Navbar = () => {
  return (
    <nav className="bg-indigo-700 border-b border-indigo-500">
      <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
        <div className="flex h-20 items-center justify-between">
          <div className="flex flex-1 items-center justify-center md:items-stretch md:justify-start">

            <NavLink className="flex flex-shrink-0 items-center mr-4" to="/">
              <img
                className="h-10 w-auto"
                src={logo}
                alt="NutriFit"
              />
              <span className="hidden md:block text-white text-2xl font-bold ml-2">
                NutriFit
              </span>
            </NavLink>

            <div className="md:ml-auto">
              <div className="flex space-x-2">
                <NavLink
                  exact
                  to="/"
                  activeClassName="text-gray-900 bg-gray-100"
                  className="text-white hover:bg-gray-900 hover:text-white rounded-md px-3 py-2"
                >
                  Home
                </NavLink>

                <NavLink
                  to="/exercises"
                  activeClassName="text-gray-900 bg-gray-100"
                  className="text-white hover:bg-gray-900 hover:text-white rounded-md px-3 py-2"
                >
                  Exercises
                </NavLink>

                <NavLink
                  to="/add-exercise"
                  activeClassName="text-gray-900 bg-gray-100"
                  className="text-white hover:bg-gray-900 hover:text-white rounded-md px-3 py-2"
                >
                  Add Exercise
                </NavLink>
              </div>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
