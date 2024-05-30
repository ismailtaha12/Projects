import React from 'react';
import { Link } from 'react-router-dom';

const ViewAllExercises = () => {
  return (
    <section className="m-auto max-w-lg my-10 px-6">
      <Link
        to='/exercises'
        className="block bg-indigo-700 text-white text-center py-4 px-6 rounded-xl hover:bg-indigo-800"
      >
        View All Exercises
      </Link>
    </section>
  );
}

export default ViewAllExercises;
