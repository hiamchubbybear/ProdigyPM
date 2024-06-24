import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import './App.css';
const opts = { 
  method: 'GET',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ title: 'React POST Request Example' })
}

export function Home () {
  return (
   <div>
   <nav>
      <Link to="/">Home</Link>
      <Link to="/parse">Data</Link>
      <Link to="/contact">Contact</Link>
      <Link to="/about">About</Link>
    </nav>
    </div>
  )
}
export function Data () {
  <div>
  <nav>
     <Link to="/">Home</Link>
     <Link to="/parse">Data</Link>
     <Link to="/contact">Contact</Link>
     <Link to="/about">About</Link>
   </nav>
   </div>
}
export function Contact () {
  return (
    <div>
    <nav>
       <Link to="/">Home</Link>
       <Link to="/parse">Data</Link>
       <Link to="/contact">Contact</Link>
       <Link to="/about">About</Link>
     </nav>
     </div>
  )
}
export function About() {
  return (
    <div>
    <nav>
       <Link to="/">Home</Link>
       <Link to="/parse">Data</Link>
       <Link to="/contact">Contact</Link>
       <Link to="/about">About</Link>
     </nav>
     </div>
  )
}
function Display(){
  return (<div>
      <Home />
      <Data />
      <Contact /> 
      <About />
  </div>)
}
export function Render({opts}) {
  const [api , getAPI] = useState(null);
  useEffect(() => {
    fetch(`https://employer-ksml.onrender.com/api/customer/allcustomer`,opts)
    .then((respone) => respone.json())
    .then(data => getAPI(data))
    .catch(err => console.log(err))
  }, []);
    if(!api) return(<p>Fetching data...</p>) 
      return (<pre>
    {JSON.stringify(api , null,2)}
    </pre>)
}
function App() {
    return (
    <div> 
      <Home />
    </div>
  );
}

export default App;
