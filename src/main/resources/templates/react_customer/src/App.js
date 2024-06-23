import { useEffect, useState } from "react";
import './App.css';
const opts = { 
  method: 'GET',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ title: 'React POST Request Example' })
}
function Render({opts}) {
  const [api , getAPI] = useState(null);
  useEffect(() => {
    fetch(`https://employer-ksml.onrender.com/api/customer/allcustomer`,opts.opts)
    .then((respone) => respone.json())
    .then(data => getAPI(data))
    .catch(err => console.log(err))
  }, []);
  if(!api) return(<p>Data is still fetching </p>) 
  return (<pre>
    {JSON.stringify(api , null,1)}
    </pre>)
}
function App() {
  return (
    <div className="App"> 
      <Render opts={opts}  />
    </div>
  );
}

export default App;
