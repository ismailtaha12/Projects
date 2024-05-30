import React from 'react';
import { useParams, useLoaderData, useNavigate } from 'react-router-dom';
import { FaArrowLeft, FaDumbbell } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';

const ExercisePage = ({ deleteExercise }) => {
  const navigate = useNavigate();
  const { id } = useParams();
  const exercise = useLoaderData();

  const onDeleteClick = (exerciseId) => {
    const confirm = window.confirm('Are you sure you want to delete this exercise?');

    if (!confirm) return;

    deleteExercise(exerciseId);

    toast.success('Exercise deleted successfully');

    navigate('/exercises');
  };

  return (
    <>
      <section>
        <div className="container m-auto py-6 px-6">
          <Link
            to="/exercises"
            className="text-indigo-500 hover:text-indigo-600 flex items-center"
          >
            <FaArrowLeft className="mr-2" /> Back to Exercise Listings
          </Link>
        </div>
      </section>

      <section className="bg-indigo-50">
        <div className="container m-auto py-10 px-6">
          <div className="grid grid-cols-1 md:grid-cols-2 w-full gap-6">
            <main>
              <div className="bg-white p-6 rounded-lg shadow-md text-center md:text-left">
                <div className="text-gray-500 mb-4">{exercise.difficultyLevel}</div>
                <h1 className="text-3xl font-bold mb-4">{exercise.title}</h1>
                <div className="text-gray-500 mb-4 flex align-middle justify-center md:justify-start">
                  <FaDumbbell className="text-orange-700, mr-1" />
                  <p className="text-orange-700">{exercise.muscleGroup}</p>
                </div>
              </div>

              <div className="bg-white p-6 rounded-lg shadow-md mt-6">
                <h3 className="text-indigo-800 text-lg font-bold mb-6">Exercise Description</h3>
                <p className="mb-4">{exercise.description}</p>
              </div>
            </main>

            <aside>
              <div className="bg-white p-6 rounded-lg shadow-md">
                <h3 className="text-xl font-bold mb-6">Exercise Info</h3>
                <h2 className="text-2xl">{exercise.title}</h2>
                <p className="my-2">Difficulty Level: {exercise.difficultyLevel}</p>
                <p className="my-2">Equipment Needed: {exercise.equipmentNeeded}</p>
              </div>

              <div className="bg-white p-6 rounded-lg shadow-md mt-6">
                <h3 className="text-xl font-bold mb-6">Manage Exercise</h3>
                <Link
                  to={`/edit-exercise/${exercise.id}`}
                  className="bg-indigo-500 hover:bg-indigo-600 text-white text-center font-bold py-2 px-4 rounded-full w-full focus:outline-none focus:shadow-outline mt-4 block"
                >
                  Edit Exercise
                </Link>
                <button
                  onClick={() => onDeleteClick(exercise.id)}
                  className="bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded-full w-full focus:outline-none focus:shadow-outline mt-4 block"
                >
                  Delete Exercise
                </button>
              </div>
            </aside>
          </div>
        </div>
      </section>
    </>
  );
};

const exerciseLoader = async ({ params }) => {
  const res = await fetch(`/api/exercises/${params.id}`);
  const data = await res.json();
  return data;
};

export { ExercisePage as default, exerciseLoader };
