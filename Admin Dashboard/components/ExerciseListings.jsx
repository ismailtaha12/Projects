import React from 'react';
import { useState, useEffect } from 'react';
import ExerciseListing from './ExerciseListing';

const ExerciseListings = ({ isHome = false }) => {

  const [exercises, setExercises] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchExercises = async () => {
      const apiUrl = isHome ? 'http://localhost:5000/exercises?_limit=3' : 'http://localhost:5000/exercises';
      try {
        const res = await fetch(apiUrl);
        const data = await res.json();
        setExercises(data);
      } catch (error) {
        console.log('Error fetching data', error);
      } finally {
        setLoading(false);
      }
    }
    fetchExercises();
  }, []);

  return (
    <section className="bg-blue-50 px-4 py-10">
      <div className="container-xl lg:container m-auto">
        <h2 className="text-3xl font-bold text-indigo-500 mb-6 text-center">
          {isHome ? 'Recent Exercises' : 'Browse Exercises'}
        </h2>
        <div className='grid grid-cols-1 md:grid-cols-3 gap-6'>
          {exercises.map((exercise) => (
            <ExerciseListing key={exercise.id} exercise={exercise} />
          ))}
        </div>
      </div>
    </section>
  );
}

export default ExerciseListings;
