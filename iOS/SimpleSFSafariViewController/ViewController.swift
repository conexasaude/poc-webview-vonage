//
//  ViewController.swift
//  SimpleSFSafariViewController
//
//  Created by Luis Alfonso Marquez Lecona on 2020-09-21.
//

import UIKit
import SafariServices

class ViewController: UIViewController {

    var safariVC = SFSafariViewController(url: URL(string: "https://hml-vonage.conexasaude.com.br/ionicroom?name=Pac._GUILHERME_MIRANDA&sessionId=2_MX40Njk3NTYzNH5-MTYyNjI4NjQ0NzI5Nn5Vc1BTeGlBMFN6R3FzMVZLdmk2TVFnTHh-fg&token=T1==cGFydG5lcl9pZD00Njk3NTYzNCZzaWc9MDZkMmRkM2Y3NTAwNzY4YzZlNzVjODdlNTZlZjI0MzMxZjFlM2FjNzpzZXNzaW9uX2lkPTJfTVg0ME5qazNOVFl6Tkg1LU1UWXlOakk0TmpRME56STVObjVWYzFCVGVHbEJNRk42UjNGek1WWkxkbWsyVFZGblRIaC1mZyZjcmVhdGVfdGltZT0xNjI2Mjg2NDgyJm5vbmNlPTAuNzM1MDAzOTkzMjA0NDg0NyZyb2xlPW1vZGVyYXRvciZleHBpcmVfdGltZT0xNjI2MzcyODgyJmluaXRpYWxfbGF5b3V0X2NsYXNzX2xpc3Q9&doctor=false&docpass=false&external=true&plataforma=CONEXA&apikey=46975634")!)
        
        override func viewDidLoad() {
            super.viewDidLoad()
            addViewControllerAsChildViewController()
        }
        
        //firstVCIdentifier
        
        func addViewControllerAsChildViewController() {
            addChild(safariVC)
            self.view.addSubview(safariVC.view)
            safariVC.didMove(toParent: self)
            self.setUpConstraints()
        }
        
        func setUpConstraints() {
            self.safariVC.view.translatesAutoresizingMaskIntoConstraints = false
            self.safariVC.view.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor, constant: 0).isActive = true
            self.safariVC.view.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor, constant: 0).isActive = true
            self.safariVC.view.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor, constant: 0).isActive = true
            self.safariVC.view.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor, constant: 0).isActive = true
            
        }
    
    override func viewDidAppear(_ animated: Bool) {
       
        
    }
    
    
}

extension ViewController: SFSafariViewControllerDelegate {
 
    func safariViewControllerDidFinish(_ controller: SFSafariViewController) {
        dismiss(animated: true)
    }
}

