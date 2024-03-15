import { useState, useEffect } from "react";

function App() {
  const [data, setData] = useState([]); //store for data get/put request

  const [todoname, setTodoname] = useState(""); // todoname input
  const [tododesc, setTododesc] = useState(""); // todo description input

  useEffect(() => {
    fetchData();
  });

  //GET Request
  const fetchData = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/todos");

      if (!response.ok) {
        throw new Error("Network response was not ok - Get method");
      }
      const data = await response.json();
      setData(data);
    } catch (error) {
      console.error(error);
    }
  };

  //handlers

  //create
  const todonameinputHandler = (event) => {
    setTodoname(event.target.value);
  };

  const tododescinputHandler = (event) => {
    setTododesc(event.target.value);
  };

  // POST Request
  const formSubmissionHandler = async (event) => {
    event.preventDefault();

    //post request input data
    const todoData = {
      taskName: todoname,
      taskDesc: tododesc,
    };

    try {
      const response = await fetch("http://localhost:8080/api/todos", {
        method: "POST",
        body: JSON.stringify(todoData),
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (!response.ok) {
        throw new Error("Network response was not ok - Post method");
      }
      await response.json();
    } catch (error) {
      console.error(error.message);
    }
  };

  // DELET Request
  const deleteHandler = async (theId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/todos/${theId}`, {
        method: "DELETE",
      });

      if (!response.ok) {
        throw new Error("Network response was not ok - Delete method");
      }
      await response.json();
    } catch (error) {
      console.error(error.message);
    }
  };

  return (
    <>
      <div className="flex items-center justify-between">
        <div className="flex-col rounded-md shadow-xl mt-20 p-12 w-64 mx-auto   bg-slate-500   items-center h-auto justify-center">
          <h1 className="text-white font-bold text-xl text-center mb-5">
            Create Todo
          </h1>
          <form onSubmit={formSubmissionHandler}>
            <label className="text-white text-sm  " htmlFor="taskname">
              Taskname
            </label>
            <input
              className="text-xs py-1 px-2 mt-1 mb-3 rounded-sm"
              onChange={todonameinputHandler}
              value={todoname}
              placeholder="Enter your task name"
              type="text"
              name="taskname"
              id="taskname"
            />
            <label className="text-white text-sm" htmlFor="taskdesc">
              Task Description
            </label>
            <input
              className="text-xs py-1 px-2 rounded-sm"
              placeholder="Enter your task desc"
              onChange={tododescinputHandler}
              value={tododesc}
              type="text"
              name="taskdesc"
              id="taskdesc"
            />
            <button
              className="bg-green-300 rounded-sm mt-5 px-3 py-1 text-sm font-thin capitalize"
              type="sumbit"
            >
              submit
            </button>
          </form>
        </div>

        <div className="w-64 rounded-md mt-20 bg-purple-200 mr-16 p-10 h-96 overflow-y-scroll">
          <div className="flex items-center justify-between">
            <h1 className="text-purple-500 text-2xl font-bold underline underline-offset-4 underline-purple-200">
              Todo's
            </h1>
            <span className="flex items-center justify-center">
              <input
                className="w-16 ml-2 px-1 text-xs py-1 rounded-sm "
                placeholder="search todo.."
                type="text"
              />
              <button
                type="submit"
                className="ml-1 bg-pink-400 px-1 w-5 rounded"
              >
                S
              </button>
            </span>
          </div>
          {data.map((item, index) => (
            <ul className="text-zinc-700 border-b border-black" key={index}>
              <li className="text-xl mt-2 font-semibold">{item.taskName}</li>
              <li className="">{item.taskDesc}</li>
              <p className="flex items-center justify-between mb-2">
                <span className="bg-green-300 text-sm px-2 rounded shadow-md cursor-pointer">
                  update
                </span>
                <span
                  onClick={() => deleteHandler(item.id)}
                  className="bg-red-300 text-sm px-2 rounded shadow-md cursor-pointer"
                >
                  delete
                </span>
              </p>
            </ul>
          ))}
        </div>
      </div>
    </>
  );
}

export default App;
