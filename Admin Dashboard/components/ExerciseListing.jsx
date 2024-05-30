import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Button } from 'reactstrap';

const ExerciseListing = ({ exercise }) => {
  const [showFullDescription, setShowFullDescription] = useState(false);

  const toggleDescription = () => {
    setShowFullDescription(prevState => !prevState);
  };

  const deleteExercise = async () => {
    fetch(`http://localhost:5000/exercises/${exercise.id}`, {
      method: 'DELETE',
    })
      .then(response => response.json())
      .then(() => console.log('Exercise deleted'));

    window.location.reload();
  };

  let description = exercise.description;
  let buttonText = showFullDescription ? 'Less' : 'More';
  let shortenedDescription = description.substring(0, 90) + '...';

  return (
    <div className="bg-black rounded-xl shadow-md text-white relative">
      <div className="p-4">
        <div className="mb-6">
          <div className="text-gray-600 my-2">{exercise.type}</div>
          <h3 className="text-xl font-bold">{exercise.title}</h3>
        </div>

        <div className="mb-5">
          {showFullDescription ? description : shortenedDescription}
        </div>

        <button onClick={toggleDescription} className="text-white mb-5 hover:text-gray-200">
          {buttonText}
        </button>

        <h3 className="text-white mb-2">Twice a week</h3>

        <div className="border border-gray-100 mb-5"></div>

        <div className="flex flex-col lg:flex-row justify-between mb-4">
          <div className="text-white mb-3">
            {exercise.location}
          </div>

          <Button
            onClick={deleteExercise}
            className="h-[36px] bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-lg text-center text-sm"
          >
            Delete
          </Button>
        </div>
      </div>
    </div>
  );
}

export default ExerciseListing;
