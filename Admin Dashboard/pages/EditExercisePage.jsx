import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

const EditExercisePage = ({ updateExerciseSubmit }) => {
  const exercise = useLoaderData();
  const [title, setTitle] = useState(exercise.title);
  const [muscleGroup, setMuscleGroup] = useState(exercise.muscleGroup);
  const [description, setDescription] = useState(exercise.description);
  const [difficultyLevel, setDifficultyLevel] = useState(exercise.difficultyLevel);
  const [equipmentNeeded, setEquipmentNeeded] = useState(exercise.equipmentNeeded);

  const navigate = useNavigate();
  const { id } = useParams();

  const submitForm = (e) => {
    e.preventDefault();

    const updatedExercise = {
      id,
      title,
      muscleGroup,
      description,
      difficultyLevel,
      equipmentNeeded,
    };

    updateExerciseSubmit(updatedExercise);

    toast.success('Exercise Updated Successfully');

    return navigate(`/exercises/${id}`);
  };

  return (
    <section className="bg-indigo-50">
      <div className="container m-auto max-w-2xl py-24">
        <div className="bg-white px-6 py-8 mb-4 shadow-md rounded-md border m-4 md:m-0">
          <form onSubmit={submitForm}>
            <h2 className="text-3xl text-center font-semibold mb-6">Edit Exercise</h2>

            <div className="mb-4">
              <label htmlFor="title" className="block text-gray-700 font-bold mb-2">
                Exercise Title
              </label>
              <input
                type="text"
                id="title"
                name="title"
                className="border rounded w-full py-2 px-3 mb-2"
                placeholder="Enter exercise title"
                required
                value={title}
                onChange={(e) => setTitle(e.target.value)}
              />
            </div>

            <div className="mb-4">
              <label htmlFor="muscleGroup" className="block text-gray-700 font-bold mb-2">
                Muscle Group
              </label>
              <input
                type="text"
                id="muscleGroup"
                name="muscleGroup"
                className="border rounded w-full py-2 px-3 mb-2"
                placeholder="Enter muscle group"
                required
                value={muscleGroup}
                onChange={(e) => setMuscleGroup(e.target.value)}
              />
            </div>

            <div className="mb-4">
              <label htmlFor="description" className="block text-gray-700 font-bold mb-2">
                Description
              </label>
              <textarea
                id="description"
                name="description"
                className="border rounded w-full py-2 px-3"
                rows="4"
                placeholder="Enter exercise description"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
              ></textarea>
            </div>

            <div className="mb-4">
              <label htmlFor="difficultyLevel" className="block text-gray-700 font-bold mb-2">
                Difficulty Level
              </label>
              <select
                id="difficultyLevel"
                name="difficultyLevel"
                className="border rounded w-full py-2 px-3"
                required
                value={difficultyLevel}
                onChange={(e) => setDifficultyLevel(e.target.value)}
              >
                <option value="Beginner">Beginner</option>
                <option value="Intermediate">Intermediate</option>
                <option value="Advanced">Advanced</option>
              </select>
            </div>

            <div className="mb-4">
              <label htmlFor="equipmentNeeded" className="block text-gray-700 font-bold mb-2">
                Equipment Needed
              </label>
              <input
                type="text"
                id="equipmentNeeded"
                name="equipmentNeeded"
                className="border rounded w-full py-2 px-3 mb-2"
                placeholder="Enter equipment needed"
                required
                value={equipmentNeeded}
                onChange={(e) => setEquipmentNeeded(e.target.value)}
              />
            </div>

            <div>
              <button
                className="bg-indigo-500 hover:bg-indigo-600 text-white font-bold py-2 px-4 rounded-full w-full focus:outline-none focus:shadow-outline"
                type="submit"
              >
                Update Exercise
              </button>
            </div>
          </form>
        </div>
      </div>
    </section>
  );
};

export default EditExercisePage;
