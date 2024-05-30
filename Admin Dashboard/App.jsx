import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import MainLayout from './layouts/MainLayout';
import HomePage from './pages/HomePage';
import ExercisesPage from './pages/ExercisesPage';
import NotFoundPage from './pages/NotFoundPage';
import ExercisePage, { exerciseLoader } from './pages/ExercisePage';
import AddExercisePage from './pages/AddExercisePage';
import EditExercisePage from './pages/EditExercisePage';
//import ExerciseListing from './ExerciseListing';
//import AddedExercisesPage from './AddedExercisesPage';

const App = () => {
  /*add new exercise*/
  const addExercise = async (newExercise) => {
    const res = await fetch('/api/exercises', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(newExercise),
    });
    return;
  }

  /*delete exercise*/
  const deleteExercise = async (id) => {
    const res = await fetch(`/api/exercises/${id}`, {
      method: 'DELETE'
    });
    return;
  }

  /*update exercise*/
  const updateExercise = async (exercise) => {
    const res = await fetch(`/api/exercises/${exercise.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(exercise),
    });
  }



  return (
    <Router>
      <Routes>
        <Route
          path="/"
          element={<MainLayout />}
        >
          <Route index element={<HomePage />} />
          <Route path="/exercises" element={<ExercisesPage />} />
          <Route path="/add-exercise" element={<AddExercisePage addExerciseSubmit={addExercise} />} />
          <Route path="/edit-exercise/:id" element={<EditExercisePage updateExerciseSubmit={updateExercise} />} />
          <Route path="/exercises/:id" element={<ExercisePage deleteExercise={deleteExercise} />} loader={exerciseLoader} />
          <Route path="*" element={<NotFoundPage />} />

        </Route>
      </Routes>
    </Router>
  );
}

export default App;


