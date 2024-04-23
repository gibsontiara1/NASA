import logo from './logo.svg';
import './App.css';
import Nav from './components/nav';
import Body from './components/Body';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';


function App() {
  return (
    <div className="App">
          <Router>
            <Nav />
            <Switch>
              <Route path="/" exact component={Body} />
            </Switch>
          </Router>
        </div>
  );
}

export default App;