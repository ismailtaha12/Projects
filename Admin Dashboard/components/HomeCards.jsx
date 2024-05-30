import React from 'react';
import { Link } from 'react-router-dom';
import Card from './Card';

const HomeCards = () => {
  return (
    <section className="py-4">
      <div className="container-xl lg:container m-auto">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 p-4 rounded-lg">

          <Card>
            <h2 className="text-2xl font-bold">For Beginners</h2>
            <p className="mt-2 mb-4">
              Start your fitness journey today
            </p>

            <Link
              to="/exercises"
              className="inline-block bg-black text-white rounded-lg px-4 py-2 hover:bg-gray-700"
            >
              Browse Exercises
            </Link>
          </Card>

          <Card>
            <h2 className="text-2xl font-bold">For Professionals</h2>
            <p className="mt-2 mb-4">
              List your favorite exercises that you'd like to add
            </p>

            <Link
              to="/add-exercise"
              className="inline-block bg-indigo-500 text-white rounded-lg px-4 py-2 hover:bg-indigo-600"
            >
              Add Exercise
            </Link>
          </Card>
        </div>
      </div>
    </section>
  )
}

export default HomeCards;
