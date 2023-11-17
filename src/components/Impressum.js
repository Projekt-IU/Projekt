import React from 'react';
import NavigationBar from './NavigationBar';
import './styles/Impressum.css';

const Impressum = () => {
    return (
        <div>
            <NavigationBar />
            <div className="impressumInhalt">
                <h1>Impressum</h1>
                <p className='projekt'>Projektarbeit IU</p>
                <p>von</p>
                <p>Phillip Suckow</p>
                <p>Tobias Knauss</p>
                <p>Jan Englert</p>
            </div>
        </div>
    );
}

export default Impressum;
