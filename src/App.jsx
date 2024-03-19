import { useState, useEffect } from "react";

import axios from "axios";

// import Styles from "./App.module.css";

const TodoPage = () => {
  const [total, setTotal] = useState([]);

  const [createForm, setCreateForm] = useState({
    taskName: "",
    taskDesc: "",
  });

  const [updateForm, setUpdateForm] = useState({
    id: null,
    taskName: "",
    taskDesc: "",
  });

  useEffect(() => {
    fetchTodo();
  }, []);

  //get the data -- GET Request

  const fetchTodo = async () => {
    const res = await axios.get("http://localhost:8080/api/todos");

    setTotal(res.data);
  };

  console.log("data fetched - Get method");

  // form input fields
  const createFormField = (event) => {
    const { name, value } = event.target;

    setCreateForm({
      ...createForm,
      [name]: value,
    });
  };

  //update input fileds
  const updateFormField = (event) => {
    const { name, value } = event.target;

    setUpdateForm({
      ...updateForm,
      [name]: value,
    });
  };

  //post the data  -- POST request

  const createTodo = async (e) => {
    e.preventDefault();

    const res = await axios.post("http://localhost:8080/api/todos", createForm);

    setTotal([...total, res.data]);

    setCreateForm({
      taskName: "",
      taskDesc: "",
    });

    console.log("data post - Post method");
  };

  // toggle handler for update the request
  const toggleUpdate = (todo) => {
    setUpdateForm({
      id: todo.id,
      taskName: todo.taskName,
      taskDesc: todo.taskDesc,
    });
  };

  // update handler --PUT Request

  const updateTodo = async (e) => {
    e.preventDefault();

    const { taskName, taskDesc } = updateForm;

    //send the update request

    const res = await axios.put("http://localhost:8080/api/todos", {
      taskName,
      taskDesc,
    });

    //update state

    const newTodos = [...total];
    const noteIndex = total.findIndex((todo) => todo.id === updateForm.id);

    newTodos[noteIndex] = res.data;

    setTotal(newTodos);

    //clear update form state
    setUpdateForm({
      id: null,
      productname: "",
      sellername: "",
    });

    console.log("data update -- PUT Method");
  };

  // delete handler  --DELETE Request

  const deleteTodo = async (id) => {
    await axios.delete(`http://localhost:8080/api/todos/${id}`);

    const newTodos = total.filter((todo) => todo.id !== id);

    setTotal(newTodos);

    console.log("data Deleted - Delete Method");
  };

  return (
    <>
      <div className="flex flex-col lg:flex-row  relative items-center justify-evenly min-h-screen bg-slate-500 ">
        <header className="absolute left-1 top-1 bg-green-400 p-3 m-3 rounded-sm text-xl font-semibold">
          CRUD App
        </header>
        <div className="flex-col bg-red p-30 mt-32 lg:mt-0 mb-12 lg:mb-0 ">
          {/*  */}
          {updateForm.id && (
            <div className="flex-col rounded-md w-60 bg-yellow-500 p-10 ">
              <h2 className="text-black text-center p-4 text-xl">
                Update Todo
              </h2>
              <form onSubmit={updateTodo}>
                <input
                  className="w-40 mb-3 px-2 rounded"
                  type="text"
                  onChange={updateFormField}
                  value={updateForm.taskName}
                  name="taskName"
                  placeholder="Enter your name"
                />
                <input
                  className="w-40 mb-3 px-2 rounded"
                  type="text"
                  onChange={updateFormField}
                  value={updateForm.taskDesc}
                  name="taskDesc"
                  placeholder="Enter your desc"
                />
                <button
                  className="bg-slate-700 text-white px-2 mt-4 rounded-sm text-md font-thin font-sans"
                  type="submit"
                >
                  Update Todo
                </button>
              </form>
            </div>
          )}
          {/*  */}
          {!updateForm.id && (
            <div className="flex-col rounded-md w-60 bg-slate-700 p-10 ">
              <h2 className="text-white text-center p-4 text-xl">
                Create Todo
              </h2>

              <form onSubmit={createTodo}>
                <input
                  className="w-40 mb-3 px-2 rounded"
                  type="text"
                  onChange={createFormField}
                  value={createForm.taskName}
                  name="taskName"
                  placeholder="Enter your name"
                />
                <input
                  className="w-40 mb-3 px-2 rounded"
                  type="text"
                  onChange={createFormField}
                  value={createForm.taskDesc}
                  name="taskDesc"
                  placeholder="Enter your desc"
                />
                <button
                  className="bg-green-300 px-2 mt-4 rounded-sm text-md font-thin font-sans"
                  type="submit"
                >
                  Create Todo
                </button>
              </form>
            </div>
          )}
        </div>
        <div className="bg-slate-900 p-4 h-96 rounded-md  [scrollbar-width:none] overflow-y-scroll">
          <h2 className="text-green-300 bg-slate-900 p-5  text-2xl sticky -top-4 ">
            TODO 'S
          </h2>
          {total.map((todo, index) => {
            return (
              <div
                className="bg-slate-400 w-56 p-2 border-b-8 border-slate-900 lg:w-96"
                key={index}
              >
                <p className="text-md capitalize font-bold">{todo.taskName}</p>
                <p className="text-md capitalize font-mono">{todo.taskDesc}</p>
                <button
                  className="bg-red-300 px-1 text-xs py-1 m-1"
                  onClick={() => deleteTodo(todo.id)}
                >
                  DeleteTodo
                </button>
                <button
                  className="bg-yellow-300 px-1 text-xs py-1 m-1"
                  onClick={() => toggleUpdate(todo)}
                >
                  UpdateTodo
                </button>
              </div>
            );
          })}
        </div>
      </div>
    </>
  );
};
export default TodoPage;
